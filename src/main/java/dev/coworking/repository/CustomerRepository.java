package dev.coworking.repository;

import dev.coworking.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM customers
            WHERE credential_id = :id
            """)
    //todo использовать jpql
    CustomerEntity findByCredentialId(@Param("id") Long id);
}
