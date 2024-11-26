package dev.coworking.service;

import dev.coworking.dto.Booking;
import dev.coworking.mapper.BookingMapper;
import dev.coworking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    //брони для определенного стола
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Booking> getBookingTable (Long tableId) {
        return bookingRepository.getListByTable(tableId)
                .stream()
                .map(bookingMapper::bookingEntityToBooking).
                collect(Collectors.toList());
    }

    //персональные брони
    @Transactional(isolation = Isolation.READ_COMMITTED) //todo нужна, тк обеспечивает безопасность и целостность данных, имеет смысл настроить уровень изоляции - читает только зафиксированные данные
    public List<Booking> getPersBooking (Long customerId) {
        return bookingRepository.getListByCustomer(customerId)
                .stream()
                .map(bookingMapper::bookingEntityToBooking).
                collect(Collectors.toList());
    }
    //апдейт и бронирование - добавление брони по id стола
    @Transactional
    public void updateBookingOrBook (Booking newBooking) {//сюда уже придет с вложенным столом
        bookingRepository.save(
                bookingMapper.bookingToBookingEntity(newBooking));// todo возможно не смапит вложенный стол
    }

    // передатировать
    @Transactional
    public void updateDate (Long id, Date date) {
        bookingRepository.updateDate(id, date);
    }

    @Transactional
    public void deleteBooking (Long id) {
        bookingRepository.deleteById(id);
    }

}
