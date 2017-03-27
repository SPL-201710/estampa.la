package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.print.PrintFont;
import catalog.models.print.PrintFontRepository;

/**
 * 
 * @author akane
 *
 */
@Service
public class PrintFontsService {
	
	@Autowired
	private PrintFontRepository repository;
	
	public PrintFontsService() {
		
	}	
	
	public PrintFont find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<PrintFont> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public PrintFont save(PrintFont item) {
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public PrintFont update(PrintFont item) {
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
