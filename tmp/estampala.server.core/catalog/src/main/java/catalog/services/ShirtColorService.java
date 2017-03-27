package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.shirt.ShirtColor;
import catalog.models.shirt.ShirtColorRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public ShirtColor update(ShirtColor item) {
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
