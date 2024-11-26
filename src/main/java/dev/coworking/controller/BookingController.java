package dev.coworking.controller;

import dev.coworking.dto.Booking;
import dev.coworking.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    //брони для определенного стола
    @GetMapping(value = "bookingTable/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getBookingTable(@PathVariable("id") Long id){
        return bookingService.getBookingTable(id);
    }

    //персональные брони
    @GetMapping(value = "bookingPers/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
