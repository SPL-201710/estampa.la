package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.shirt.ShirtColor;
import catalog.models.shirt.ShirtColorRepository;

/**
 * 
 * @author akane
 *
 */
public class ShirtColorService {
	
	@Autowired
	private ShirtColorRepository repository;
	
	public ShirtColorService() {
		
	}
	
	public ShirtColor find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<ShirtColor> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public ShirtColor save(ShirtColor item) {
		return repository.save(item);
	}	
	
	public ShirtColor update(ShirtColor item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
