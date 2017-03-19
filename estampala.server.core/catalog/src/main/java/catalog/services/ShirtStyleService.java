package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.shirt.ShirtStyle;
import catalog.models.shirt.ShirtStyleRepository;

/**
 * 
 * @author akane
 *
 */
public class ShirtStyleService {
	
	@Autowired
	private ShirtStyleRepository repository;
	
	public ShirtStyleService() {
		
	}
	
	public ShirtStyle find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<ShirtStyle> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public ShirtStyle save(ShirtStyle item) {
		return repository.save(item);
	}	
	
	public ShirtStyle update(ShirtStyle item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
