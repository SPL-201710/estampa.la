package users.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import users.models.Role;
import users.models.RoleRepository;

/**
 * 
 * @author akane
 *
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public RoleService() {
		
	}
	
	public Role find(UUID id) {		
		return repository.findOne(id);		
	}
	
	public Page<Role> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public Role save(Role item) {	
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public Role update(Role item) {
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
