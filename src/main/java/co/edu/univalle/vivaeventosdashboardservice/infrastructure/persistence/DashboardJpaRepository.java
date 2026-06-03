package co.edu.univalle.vivaeventosdashboardservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardJpaRepository
        extends JpaRepository<DashboardEntity, Long> {
}