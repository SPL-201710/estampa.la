package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.print.TextStyle;
import catalog.models.print.TextStyleRepository;

/**
 * 
 * @author akane
 *
 */
@Service
public class TextStyleService {
	
	@Autowired
	private TextStyleRepository repository;
	
	public TextStyleService() {
		
	}	
	
	public TextStyle find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<TextStyle> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public TextStyle save(TextStyle item) {
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public TextStyle update(TextStyle item) {
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
