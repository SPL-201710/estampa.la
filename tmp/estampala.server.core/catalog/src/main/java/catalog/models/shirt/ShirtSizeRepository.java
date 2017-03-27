package catalog.models.shirt;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtSizeRepository extends JpaRepository<ShirtSize, UUID> {
    
}
