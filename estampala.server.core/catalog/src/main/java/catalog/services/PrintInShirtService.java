package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.product.PrintInShirt;
import catalog.models.product.PrintInShirtRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public PrintInShirt update(PrintInShirt item) {
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
