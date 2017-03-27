package catalog.models.print;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintFontRepository extends JpaRepository<PrintFont, UUID> {    
}
