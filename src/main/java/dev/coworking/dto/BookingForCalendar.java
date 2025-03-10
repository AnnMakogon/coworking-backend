package dev.coworking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingForCalendar {

    private Long id;

    private LocalDate date;

    private String fioCustomer;

    private Long idCustomer;
}
