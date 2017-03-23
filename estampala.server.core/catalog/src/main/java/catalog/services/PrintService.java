package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import catalog.models.print.Print;
import catalog.models.print.PrintRepository;

/**
 * 
 * @author akane
 *
 */
@Service
public class PrintService {
	
	@Autowired
	private PrintRepository repository;
	
	public PrintService() {
		
	}
	
	public Print find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<Print> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize, Sort.Direction.DESC, "name");
		return repository.findAll(pageRequest);
	}
	
	public Print save(Print item) {
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public Print update(Print item) {
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
