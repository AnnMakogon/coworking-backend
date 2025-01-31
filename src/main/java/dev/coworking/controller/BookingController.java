package dev.coworking.controller;

import dev.coworking.dto.Booking;
import dev.coworking.dto.BookingForCalendar;
import dev.coworking.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    //брони для определенного стола на нынешний месяц
    @GetMapping(value = "bookingTable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Booking> getBookingTable(@PathVariable("id") Long id, @RequestParam("date") LocalDate date){
        return bookingService.getBookingTable(id, date);
    }

    //брони для определенного стола на определенный месяц
    @GetMapping(value = "bookingToCalendar/{id}/{month}/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingForCalendar> getBookingToCalendar(@PathVariable("id") Long id, @PathVariable("month") int month, @PathVariable("year") int year){
        return bookingService.getBookingToCalendar(id, month, year);
    }

    //персональные брони
    @GetMapping(value = "bookingPers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getBookingPers(@PathVariable("id") Long id){
        return bookingService.getPersBooking(id);
    }

    //апдейт и бронирование - добавление брони по id стола
    @PutMapping(value = "booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateBookingOrBook(@RequestBody Booking newBooking){
        bookingService.updateBookingOrBook(newBooking);
    }

    // передатировать
    @PutMapping(value = "bookingUpdateDate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDate(@RequestParam Long id, @RequestParam Date date) {
        bookingService.updateDate(id, date);
    }

    @DeleteMapping(value = "booking/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id){
        bookingService.deleteBooking(id);
    }

}
