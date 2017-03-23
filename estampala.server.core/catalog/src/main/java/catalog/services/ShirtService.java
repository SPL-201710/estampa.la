package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.shirt.Shirt;
import catalog.models.shirt.ShirtRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public Shirt update(Shirt item) {
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
