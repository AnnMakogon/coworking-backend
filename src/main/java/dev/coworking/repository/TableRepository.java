package dev.coworking.repository;

import dev.coworking.entity.TableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT t.id, t.number, t.description, t.price, null AS workspace_id 
            FROM tables t 
            LEFT JOIN workspaces w ON w.id = t.workspace_id 
            WHERE t.workspace_id = :id""")
    Page<TableEntity> getListByWorkspace(@Param("id") Long id, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = """
            UPDATE tables 
            SET description = :description, price = :price
            WHERE id = :id
            """)
    void updateTable(@Param("description") String description,
                     @Param("price") BigDecimal price,
                     @Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
            DELETE FROM tables 
            WHERE id = :id
            """)
    void myDeleteById(@Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
            DELETE FROM bookings 
            WHERE table_id = :id
            """)
    void deleteBookingsByTableId(@Param("id") Long id);
}
