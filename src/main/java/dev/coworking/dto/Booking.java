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
public class Booking {

    private Long id;

    private LocalDate date;

    private Table table;

    private Customer customer;
}
