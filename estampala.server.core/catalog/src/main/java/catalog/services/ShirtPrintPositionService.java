package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.shirt.ShirtPrintPosition;
import catalog.models.shirt.ShirtPrintPositionRepository;

/**
 * 
 * @author akane
 *
 */
public class ShirtPrintPositionService {
	
	@Autowired
	private ShirtPrintPositionRepository repository;
	
	public ShirtPrintPositionService() {
		
	}
	
	public ShirtPrintPosition find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<ShirtPrintPosition> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public ShirtPrintPosition save(ShirtPrintPosition item) {
		return repository.save(item);
	}	
	
	public ShirtPrintPosition update(ShirtPrintPosition item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
