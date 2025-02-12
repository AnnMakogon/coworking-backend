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
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    //брони для определенного стола на нынешний месяц
    @GetMapping(value = "bookingTable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Booking> getBookingTable(@PathVariable("id") Long id, @RequestParam("date") LocalDate date) {
        return bookingService.getBookingTable(id, date);
    }

    //брони для определенного стола на определенный месяц
    @GetMapping(value = "bookingToCalendar/{id}/{month}/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingForCalendar> getBookingToCalendar(@PathVariable("id") Long id, @PathVariable("month") int month, @PathVariable("year") int year) {
        return bookingService.getBookingToCalendar(id, month, year);
    }

    //персональные брони
    @GetMapping(value = "bookingPers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getBookingPers(@PathVariable("id") Long id) {
        return bookingService.getPersBooking(id);
    }

    //апдейт
    @PutMapping(value = "booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateBooking(@RequestBody Booking newBooking) {
        bookingService.updateBooking(newBooking);
    }

    // передатировать
    @PutMapping(value = "bookingUpdateDate/{id}") //здесь просто строку принимает
    public void updateDate(@PathVariable("id") Long id, @RequestBody String date) {
        bookingService.updateDate(id, date);
    }

    @DeleteMapping(value = "booking/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
    }

    //бронирование - добавление брони по id стола
    @PostMapping(value = "booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createBooking(@RequestBody Booking newBooking) {
        bookingService.book(newBooking); //сюда приходит с workspace и customer только с id остальное - пустое
    }

    @GetMapping(value = "workspaceToMyBooking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Booking> getWorkspaceToMyBooking(@PathVariable("id") Long id) {
        return bookingService.getMyBooking(id);
    }

}
