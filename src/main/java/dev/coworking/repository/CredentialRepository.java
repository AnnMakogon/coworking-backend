package dev.coworking.repository;

import dev.coworking.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    @Query("SELECT c FROM CredentialEntity c JOIN FETCH c.password p WHERE c.email = :email")
    CredentialEntity findCredentialByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = """
            SELECT c.*
            FROM credentials c
            WHERE c.email = :email
            """)
    //todo где можно используй базовые фетчи
    CredentialEntity findDetailsByEmail(@Param("email") String email);

}
