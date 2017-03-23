package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.theme.Theme;
import catalog.models.theme.ThemeRepository;

/**
 * 
 * @author akane
 *
 */
@Service
public class ThemeService {
	
	@Autowired
	private ThemeRepository repository;
	
	public ThemeService() {
		
	}
	
	public Theme find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<Theme> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public Theme save(Theme item) {
		if (item != null){
			item.setId(UUID.randomUUID());
		}
		return repository.save(item);
	}	
	
	public Theme update(Theme item) {
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
