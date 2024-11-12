package dev.coworking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="castomers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "castomer_seq", sequenceName = "castomer_seq", allocationSize = 1)

public class CastomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "castomer_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CredentialEntity credential;

    private String fio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "castomer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookings;
}
