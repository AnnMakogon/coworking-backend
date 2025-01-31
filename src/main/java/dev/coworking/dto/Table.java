package dev.coworking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Table {

    private Long id;

    private Integer number;
    private String description;

    private Workspace workspace;

    private List<Booking> bookings;

    private BigDecimal price;
}
