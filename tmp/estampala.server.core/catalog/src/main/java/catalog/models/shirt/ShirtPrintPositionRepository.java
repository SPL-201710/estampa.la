package catalog.models.shirt;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtPrintPositionRepository extends JpaRepository<ShirtPrintPosition, UUID> {
    
}
