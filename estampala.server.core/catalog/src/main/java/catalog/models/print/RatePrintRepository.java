package catalog.models.print;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePrintRepository extends JpaRepository<RatePrint, UUID> {  
	
	public List<RatePrint> findByIdUserAndIdPrint(UUID idUser, UUID idPrint);
}
