package dev.coworking.repository;

import dev.coworking.entity.CastomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CastomerEntity, Long> {
}
