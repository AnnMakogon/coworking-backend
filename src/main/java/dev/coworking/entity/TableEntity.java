package dev.coworking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "table_seq", sequenceName = "table_seq", allocationSize = 1)
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_seq")
    private Long id;

    private Integer number;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_id")
    private WorkspaceEntity workspace = new WorkspaceEntity();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookings = new ArrayList<>();

    private BigDecimal price;
}
