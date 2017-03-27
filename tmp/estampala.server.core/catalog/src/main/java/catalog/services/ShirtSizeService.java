package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.shirt.ShirtSize;
import catalog.models.shirt.ShirtSizeRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public ShirtSize update(ShirtSize item) {
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
