package dev.coworking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq", allocationSize = 1)

public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    private Long id;

    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "castomer_id")
    private CastomerEntity castomer;

}
