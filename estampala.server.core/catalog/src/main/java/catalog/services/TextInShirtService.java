package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.product.TextInShirt;
import catalog.models.product.TextInShirtRepository;

/**
 * 
 * @author akane
 *
 */
public class TextInShirtService {
	
	@Autowired
	private TextInShirtRepository repository;
	
	public TextInShirtService() {
		
	}
	
	public TextInShirt find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<TextInShirt> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public TextInShirt save(TextInShirt item) {
		return repository.save(item);
	}	
	
	public TextInShirt update(TextInShirt item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
