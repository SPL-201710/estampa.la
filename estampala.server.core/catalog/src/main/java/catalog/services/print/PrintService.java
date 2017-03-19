package catalog.services.print;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import catalog.models.print.Print;
import catalog.models.print.PrintRepository;

/**
 * 
 * @author akane
 *
 */
public class PrintService {
	
	@Autowired
	private PrintRepository printRepository;
	
	public PrintService() {
		
	}
	
	public Print find(UUID id) {
		return printRepository.findOne(id);
	}
	
	public Page<Print> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize, Sort.Direction.DESC, "name");
		return printRepository.findAll(pageRequest);
	}
	
	public Print save(Print print) {
		return printRepository.save(print);
	}	
	
	public Print update(Print print) {
		return printRepository.save(print);
	}	
	
	public void delete(UUID id){		
		printRepository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return printRepository.exists(id);						
	}
}
