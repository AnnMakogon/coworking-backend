package dev.coworking.service;

import dev.coworking.dto.Booking;
import dev.coworking.dto.BookingForCalendar;
import dev.coworking.entity.BookingEntity;
import dev.coworking.mapper.BookingMapper;
import dev.coworking.mapper.TableMapper;
import dev.coworking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
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
    private final TableMapper tableMapper;//для маппинга

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
    public void updateBookingOrBook(Booking newBooking) {//сюда уже придет с вложенным столом
        bookingRepository.save(
                bookingMapper.bookingToBookingEntity(newBooking, tableMapper));
    }

    // передатировать
    @Transactional
    public void updateDate(Long id, Date date) {
        bookingRepository.updateDate(id, date);
    }

    @Transactional
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
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

}
