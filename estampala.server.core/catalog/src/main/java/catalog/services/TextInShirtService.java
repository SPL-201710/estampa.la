package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.models.product.TextInShirt;
import catalog.models.product.TextInShirtRepository;

/**
 * 
 * @author akane
 *
 */
@Service
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
		if (item != null){
			return repository.save(item);
		}
		return null;
	}	
	
	public TextInShirt update(TextInShirt item) {
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
