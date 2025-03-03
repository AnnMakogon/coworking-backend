package dev.coworking.service.InitalServices;

import dev.coworking.entity.BookingEntity;
import dev.coworking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InitialBookings {

    //todo накатывать данные через liquibase
    private final BookingRepository bookingRepository;

    @Transactional
    public void init() {
        BookingEntity booking0 = new BookingEntity(
                null,
                LocalDate.now(),
                null,
                null
        );

        BookingEntity booking1 = new BookingEntity(
                null,
                LocalDate.now(),
                null,
                null
        );

        bookingRepository.save(booking0);
        bookingRepository.save(booking1);
    }


}
