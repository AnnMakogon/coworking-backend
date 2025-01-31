package dev.coworking.repository;

import dev.coworking.entity.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT b.* 
            FROM bookings b 
            LEFT JOIN tables t ON t.id = b.table_id 
            WHERE t.id = :id  
            AND EXTRACT(MONTH FROM b.date) = EXTRACT(MONTH FROM CAST(:date AS DATE)) 
            AND EXTRACT(YEAR FROM b.date) = EXTRACT(YEAR FROM CAST(:date AS DATE)) 
            """)
    Page<BookingEntity> getListByTable(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE bookings SET date = :date WHERE id = :id")
    void updateDate(@Param("id") Long id, @Param("date") Date date);

    @Query(nativeQuery = true, value = """
            SELECT b.* 
            FROM bookings b 
            LEFT JOIN tables t ON t.id = b.customer_id 
            WHERE t.id = :id""")
    List<BookingEntity> getListByCustomer(@Param("id") Long id);

    @Query(nativeQuery = true, value = """
            SELECT c.id AS customer_id, c.fio AS customer_fio, b.date, b.table_id
            FROM bookings b 
            LEFT JOIN tables t ON t.id = b.table_id 
            LEFT JOIN customers c ON c.id = b.customer_id 
            WHERE t.id = :id  
            AND DATE_PART('month', b.date) = :month
            AND DATE_PART('year', b.date) = :year """)
    List<Object[]> getBookingToCalendar(@Param("id") Long id, @Param("month") int month, @Param("year") int year);
}
