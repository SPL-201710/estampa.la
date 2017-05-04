package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.exceptions.S3ImageUploadException;
import catalog.models.shirt.ShirtStyle;
import catalog.models.shirt.ShirtStyleRepository;
import catalog.pojos.ShirtStyleCreator;
import catalog.utils.S3Folders;
import catalog.utils.S3Util;

/**
 * 
 * @author akane
 *
 */
@Service
public class ShirtStyleService {
	
	@Autowired
	private ShirtStyleRepository repository;
	
	@Autowired
	private S3Util s3Util;
	
	public ShirtStyleService() {
		
	}
	
	public ShirtStyle find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<ShirtStyle> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public ShirtStyle save(ShirtStyleCreator item) throws S3ImageUploadException {
		if (item != null){			
			String imageUrl = s3Util.upload(item.getImage(), item.getImageExtension(), S3Folders.SHIRT_STYLES);
			
			ShirtStyle shirtStyle = new ShirtStyle(UUID.randomUUID(), item.getName(), imageUrl);
			
			return repository.save(shirtStyle);
		}
		return null;
	}	
	
	public ShirtStyle update(ShirtStyleCreator item) throws S3ImageUploadException {
		if (item != null){
			String imageUrl = s3Util.upload(item.getImage(), item.getImageExtension(), S3Folders.SHIRT_STYLES);
			
			ShirtStyle shirtStyle = new ShirtStyle(item.getId(), item.getName(), imageUrl);
			
			return repository.save(shirtStyle);
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
