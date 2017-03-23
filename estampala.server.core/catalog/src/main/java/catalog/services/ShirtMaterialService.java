package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.shirt.ShirtMaterial;
import catalog.models.shirt.ShirtMaterialRepository;

/**
 * 
 * @author akane
 *
 */
@Service
public class ShirtMaterialService {
	
	@Autowired
	private ShirtMaterialRepository repository;
	
	public ShirtMaterialService() {
		
	}
	
	public ShirtMaterial find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<ShirtMaterial> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public ShirtMaterial save(ShirtMaterial item) {
		if (item != null){
			item.setId(UUID.randomUUID());
		}
		return repository.save(item);
	}	
	
	public ShirtMaterial update(ShirtMaterial item) {
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
