package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import catalog.models.product.Product;
import catalog.models.product.ProductRepository;

/**
 * 
 * @author akane
 *
 */
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public ProductService() {
		
	}
	
	public Product find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<Product> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public Product save(Product item) {
		return repository.save(item);
	}	
	
	public Product update(Product item) {
		return repository.save(item);
	}	
	
	public void delete(UUID id){		
		repository.delete(id);						
	}
	
	public boolean exists(UUID id){		
		return repository.exists(id);						
	}
}
