package dev.coworking.repository;

import dev.coworking.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM managers
            where credential_id = :id
            """)
    ManagerEntity getManagerByCredential(@Param("id")Long id);
}
