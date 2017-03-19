package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.product.PrintInShirt;
import catalog.models.product.PrintInShirtRepository;

/**
 * 
 * @author akane
 *
 */
public class PrintInShirtService {
	
	@Autowired
	private PrintInShirtRepository repository;
	
	public PrintInShirtService() {
		
	}
	
	public PrintInShirt find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<PrintInShirt> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public PrintInShirt save(PrintInShirt item) {
		return repository.save(item);
	}	
	
	public PrintInShirt update(PrintInShirt item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
