package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.shirt.ShirtPrintPosition;
import catalog.models.shirt.ShirtPrintPositionRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			item.setId(UUID.randomUUID());
		}
		return repository.save(item);
	}	
	
	public ShirtPrintPosition update(ShirtPrintPosition item) {
		if (item != null){
			return repository.save(item);
		}
		
		return null;
	}	
	
	public void delete(UUID id){
		if(id != null){
			repository.delete(id);
		}
	}
	
	public boolean exists(UUID id){
		if(id != null){
			return repository.exists(id);
		}
		
		return false;
	}
}
