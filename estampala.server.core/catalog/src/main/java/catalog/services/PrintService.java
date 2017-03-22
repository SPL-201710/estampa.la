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
		return repository.save(item);
	}	
	
	public Print update(Print item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
