package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import catalog.exceptions.ThemeNotFoundException;
import catalog.models.print.Print;
import catalog.models.print.PrintRepository;
import catalog.models.theme.Theme;
import catalog.pojos.PrintCreator;
import catalog.utils.S3Folders;
import catalog.utils.S3Util;
import commons.exceptions.EstampalaException;

/**
 *
 * @author akane
 *
 */
@Service
public class PrintService {

	@Autowired
	private PrintRepository repository;

	@Autowired
	private ThemeService themeService;

	@Autowired
	private S3Util s3Util;
	
	public PrintService() {

	}

	public Print find(UUID id) {
		return repository.findOne(id);
	}

	public Page<Print> findAll(int page, int pageSize, String popularity, Specification<Print> spec) {

		Direction direction = Sort.Direction.DESC;
		if (popularity != null && "asc".equalsIgnoreCase(popularity)){
			direction = Sort.Direction.ASC;
		}

		PageRequest pageRequest = new PageRequest(page - 1, pageSize, direction, "popularity");
		return repository.findAll(spec, pageRequest);
	}
	
	public Print save(PrintCreator item) throws EstampalaException {
		if (item != null){

			UUID owner = item.getOwner();

			Theme theme = themeService.find(item.getTheme());
			if (theme == null){
				throw new ThemeNotFoundException();
			}
			
			String imageUrl = s3Util.upload(item.getImage(), item.getImageExtension(), S3Folders.PRINTS);			
			
			Print print = new Print(UUID.randomUUID(), item.getDescription(), imageUrl, item.getName(), item.getPrice(), item.getRating(), item.getPopularity(), theme, owner, item.getOwnerUsername());

			return repository.save(print);
		}

		return null;
	}
	
	public Print update(PrintCreator item) throws EstampalaException {
		if (item != null){

			Theme theme = themeService.find(item.getTheme());
			if (theme == null){
				throw new ThemeNotFoundException();
			}

			UUID owner = item.getOwner();			
			String imageUrl = s3Util.upload(item.getImage(), item.getImageExtension(), S3Folders.PRINTS);

			Print print = find(item.getPrint());
			print.setDescription(item.getDescription());
			print.setImage(imageUrl);
			print.setName(item.getName());
			print.setPrice(item.getPrice());
			print.setRating(item.getRating());
			print.setPopularity(item.getPopularity());
			print.setTheme(theme);
			print.setOwner(owner);

			return repository.save(print);
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
