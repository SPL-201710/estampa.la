package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import catalog.models.theme.Theme;
import catalog.models.print.Print;
import catalog.models.print.PrintRepository;
import commons.exceptions.EstampalaException;
import catalog.exceptions.ThemeNotFoundException;
import catalog.pojos.PrintCreator;

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

	public PrintService() {

	}

	public Print find(UUID id) {
		return repository.findOne(id);
	}

	public Page<Print> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize, Sort.Direction.DESC, "name");
		return repository.findAll(pageRequest);
	}

	public Print save(PrintCreator item) throws EstampalaException {
		if (item != null){

			Theme theme = themeService.find(item.getTheme());
			if (theme == null){
				throw new ThemeNotFoundException();
			}

			Print print = new Print(UUID.randomUUID(), item.getDescription(), item.getImage(), item.getName(),
					item.getPrice(), item.getRating(), theme);
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

			Print print = find(item.getPrint());
			print.setDescription(item.getDescription());
			print.setImage(item.getImage());
			print.setName(item.getName());
			print.setPrice(item.getPrice());
			print.setRating(item.getRating());
			print.setTheme(theme);

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
