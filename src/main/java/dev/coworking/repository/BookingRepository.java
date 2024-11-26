package dev.coworking.repository;

import dev.coworking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT b.* 
            FROM bookings b 
            LEFT JOIN tables t ON t.id = b.tables_id 
            WHERE t.id = :id """)
    List<BookingEntity> getListByTable(@Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE bookings SET date = :date WHERE id = :id")
    void updateDate(@Param("id") Long id,@Param("date") Date date);

    @Query(nativeQuery = true, value = """
            SELECT b.* 
            FROM bookings b 
            LEFT JOIN tables t ON t.id = b.customer_id 
            WHERE t.id = :id""")
    List<BookingEntity> getListByCustomer(@Param("id") Long id);
}
