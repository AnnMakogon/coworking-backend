package dev.coworking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private Long id;

    private Credential credential;

    private String fio;

    private List<Booking> bookings;
}
