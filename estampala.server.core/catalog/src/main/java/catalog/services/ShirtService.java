package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.shirt.Shirt;
import catalog.models.shirt.ShirtRepository;

/**
 * 
 * @author akane
 *
 */
public class ShirtService {
	
	@Autowired
	private ShirtRepository repository;
	
	public ShirtService() {
		
	}
	
	public Shirt find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<Shirt> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public Shirt save(Shirt item) {
		return repository.save(item);
	}	
	
	public Shirt update(Shirt item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
