package dev.coworking.repository;

import dev.coworking.dto.Credential;
import dev.coworking.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT p.password
            FROM credentials c
            JOIN passwords p ON c.password_id = p.id
            WHERE c.email = :email
            """)
    String findPasswordByEmail(@Param("email") String email);

}
