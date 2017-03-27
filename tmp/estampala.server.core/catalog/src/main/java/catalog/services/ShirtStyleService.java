package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.shirt.ShirtStyle;
import catalog.models.shirt.ShirtStyleRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public ShirtStyle update(ShirtStyle item) {
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
