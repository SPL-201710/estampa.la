package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.shirt.ShirtSize;
import catalog.models.shirt.ShirtSizeRepository;

/**
 * 
 * @author akane
 *
 */
public class ShirtSizeService {
	
	@Autowired
	private ShirtSizeRepository repository;
	
	public ShirtSizeService() {
		
	}
	
	public ShirtSize find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<ShirtSize> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public ShirtSize save(ShirtSize item) {
		return repository.save(item);
	}	
	
	public ShirtSize update(ShirtSize item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
