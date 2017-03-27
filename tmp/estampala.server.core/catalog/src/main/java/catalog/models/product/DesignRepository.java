package catalog.models.product;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, UUID> {
    
}
