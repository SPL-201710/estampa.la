package catalog.models.shirt;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtMaterialRepository extends JpaRepository<ShirtMaterial, UUID> {
    
}
