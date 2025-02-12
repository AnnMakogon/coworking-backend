package dev.coworking.service;

import dev.coworking.dto.*;
import dev.coworking.entity.BookingEntity;
import dev.coworking.entity.CustomerEntity;
import dev.coworking.entity.TableEntity;
import dev.coworking.entity.WorkspaceEntity;
import dev.coworking.mapper.BookingMapper;
import dev.coworking.mapper.TableMapper;
import dev.coworking.repository.BookingRepository;
import dev.coworking.repository.CustomerRepository;
import dev.coworking.repository.TableRepository;
import dev.coworking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingService {

    @Lazy
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final WorkspaceRepository workspaceRepository;
    private final TableMapper tableMapper;//для маппинга
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;

    //брони для определенного стола в нынешний месяц
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Page<Booking> getBookingTable(Long tableId, LocalDate date) {
        return mapDtoForPage(bookingRepository.getListByTable(tableId, date, null));
    }

    private Page<Booking> mapDtoForPage(Page<BookingEntity> bookings) {
        List<Booking> tabledtos = bookingMapper.bookingEntityListToBookingList(bookings.getContent());
        return new PageImpl<>(tabledtos, bookings.getPageable(), bookings.getTotalElements());
    }

    //персональные брони
    @Transactional(isolation = Isolation.READ_COMMITTED)
    //todo нужна, тк обеспечивает безопасность и целостность данных, имеет смысл настроить уровень изоляции - читает только зафиксированные данные
    public List<Booking> getPersBooking(Long customerId) {
        return bookingRepository.getListByCustomer(customerId)
                .stream()
                .map(bookingEntity -> bookingMapper.bookingEntityToBooking(bookingEntity, tableMapper)).
                collect(Collectors.toList());
    }

    //апдейт и бронирование - добавление брони по id стола
    @Transactional
    public void updateBooking(Booking newBooking) {//сюда уже придет с вложенным столом
        bookingRepository.save(
                bookingMapper.bookingToBookingEntity(newBooking, tableMapper));
    }

    // передатировать
    @Transactional
    public void updateDate(Long id, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        Date newDate = Timestamp.valueOf(localDateTime);
        bookingRepository.updateDate(id, newDate);
    }

    @Transactional
    public void deleteBooking(Long id) {
        bookingRepository.myDeleteById(id);
    }

    // брони на определённый месяц
    @Transactional
    public List<BookingForCalendar> getBookingToCalendar(Long id, int month, int year) {
        List<Object[]> results = bookingRepository.getBookingToCalendar(id, month, year);
        List<BookingForCalendar> bookings = new ArrayList<>();

        for (Object[] row : results) {
            BookingForCalendar booking = mapToBookingForCalendar(row);
            bookings.add(booking);
        }
        return bookings;
    }

    private BookingForCalendar mapToBookingForCalendar(Object[] row) {
        Long id = (Long) row[3];
        Timestamp timestamp = (Timestamp) row[2];
        String fioCustomer = (String) row[1];
        Long idCustomer = (Long) row[0];

        LocalDate date = timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null;

        return new BookingForCalendar(id, date, fioCustomer, idCustomer);
    }

    @Transactional
    public void book(Booking newBooking) {
        WorkspaceEntity workspace = workspaceRepository.findById(newBooking.getTable().getWorkspace().getId())
                .orElseThrow(() -> new RuntimeException("Workspace not found"));

        TableEntity existingTableEntity = tableRepository.findById(newBooking.getTable().getId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        BookingEntity bookingEntity = bookingMapper.bookingToBookingEntity(newBooking, tableMapper);
        bookingEntity.setDate(newBooking.getDate());

        bookingEntity.setTable(existingTableEntity);

        existingTableEntity.getBookings().add(bookingEntity);

        bookingEntity.getTable().setWorkspace(workspace);

        CustomerEntity customerEntity = customerRepository.findByCredentialId(newBooking.getCustomer().getCredential().getId());

        customerEntity.getBookings().add(bookingEntity);
        bookingEntity.setCustomer(customerEntity);

        bookingRepository.save(bookingEntity);

    }

    @Transactional
    public Page<Booking> getMyBooking(Long idCredential) {

        Page<BookingEntity> bookingEntities = bookingRepository.getMyBookingByCustomerId(idCredential, null);

        List<Booking> bookings = bookingEntities.getContent().stream()
                .map(bookingEntity -> {
                    Booking booking = new Booking();
                    booking.setId(bookingEntity.getId());
                    booking.setDate(bookingEntity.getDate());

                    Table tableDto = new Table();
                    TableEntity tableEntity = bookingEntity.getTable();
                    if (tableEntity != null) {
                        tableDto.setId(tableEntity.getId());
                        tableDto.setNumber(tableEntity.getNumber());
                        tableDto.setDescription(tableEntity.getDescription());
                        tableDto.setPrice(tableEntity.getPrice());

                        Workspace workspaceDto = new Workspace();
                        WorkspaceEntity workspaceEntity = tableEntity.getWorkspace();
                        if (tableEntity.getWorkspace() != null) {
                            workspaceDto = new Workspace(workspaceEntity.getId(), workspaceEntity.getName(),
                                    workspaceEntity.getDescription(), workspaceEntity.getSchedule(),
                                    workspaceEntity.getLatitude(), workspaceEntity.getLongitude(),
                                    workspaceEntity.getAddress(), new ArrayList<>(), new ArrayList<>());
                        }
                        tableDto.setWorkspace(workspaceDto);
                    }
                    booking.setTable(tableDto);

                    Customer customerDto = new Customer();
                    CustomerEntity customerEntity = bookingEntity.getCustomer();
                    if (customerEntity != null) {
                        customerDto.setId(customerEntity.getId());
                        customerDto.setFio(customerEntity.getFio());
                    }
                    booking.setCustomer(customerDto);

                    return booking;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(bookings, bookingEntities.getPageable(), bookingEntities.getTotalElements());
    }

}
