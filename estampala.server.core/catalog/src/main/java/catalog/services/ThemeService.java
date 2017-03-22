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
		return repository.save(item);
	}	
	
	public Theme update(Theme item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
