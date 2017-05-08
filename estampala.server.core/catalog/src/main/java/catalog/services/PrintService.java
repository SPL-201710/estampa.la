package catalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import catalog.exceptions.PrintNotFoundException;
import catalog.exceptions.ThemeNotFoundException;
import catalog.models.print.Print;
import catalog.models.print.PrintRepository;
import catalog.models.print.RatePrint;
import catalog.models.print.RatePrintRepository;
import catalog.models.theme.Theme;
import catalog.pojos.PrintCreator;
import catalog.utils.S3Folders;
import catalog.utils.S3Util;
import commons.exceptions.EstampalaException;
import commons.exceptions.OwnerNotFoundException;
import commons.responses.SuccessResponse;
import commons.util.Endpoints;
import commons.util.EstampalaTools;

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
	private RatePrintRepository rateRepository;
	
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
			List<String> pathParameters = new ArrayList<String>();			
			pathParameters.add(owner.toString());
			
			SuccessResponse res = EstampalaTools.invokeGetRestServices(Endpoints.USERS_EXIST, pathParameters, null, SuccessResponse.class);
			if (res == null || !res.isSuccess()){
				throw new OwnerNotFoundException(owner.toString());
			}
			
			Theme theme = themeService.find(item.getTheme());
			if (theme == null){
				throw new ThemeNotFoundException();
			}
			
			String imageUrl = s3Util.upload(item.getImage(), item.getImageExtension(), S3Folders.PRINTS);			
			
			Print print = new Print(UUID.randomUUID(), item.getDescription(), imageUrl, item.getName(), item.getPrice(), 0, 0, theme, owner, item.getOwnerUsername(), 0l);

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
						
			String imageUrl = s3Util.upload(item.getImage(), item.getImageExtension(), S3Folders.PRINTS);

			UUID owner = item.getOwner();
			List<String> pathParameters = new ArrayList<String>();			
			pathParameters.add(owner.toString());
			
			SuccessResponse res = EstampalaTools.invokeGetRestServices(Endpoints.USERS_EXIST, pathParameters, null, SuccessResponse.class);
			if (res == null || !res.isSuccess()){
				throw new OwnerNotFoundException(owner.toString());
			}
			
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
	
	public RatePrint rate(UUID idPrint, String idUser, float rate) throws EstampalaException{		
		if (rate < 0f){
			rate = 0f;
		}
		
		if (rate > 10f){
			rate = 10f;
		}
		
		if (idUser == null || idUser.isEmpty()){
			throw new OwnerNotFoundException(idUser);
		}
		
		Print print = repository.findOne(idPrint);
		if (print == null){
			throw new PrintNotFoundException();
		}
		
		float rating = print.getRating();
		long counts = print.getRatingCounts();
		
		List<RatePrint> ratePrints = rateRepository.findByIdUserAndIdPrint(UUID.fromString(idUser), idPrint);
		RatePrint ratePrint = null;
		if (ratePrints != null && !ratePrints.isEmpty()){
			ratePrint = ratePrints.get(0);
			float original = (rating * counts) - ratePrint.getRate();
			print.setRating((original + rate) / counts);
			ratePrint.setRate(rate);
		}
		else{
			print.setRating((rating + rate) / 2);
			print.setRatingCounts(counts + 1);
			ratePrint = new RatePrint(UUID.randomUUID(), UUID.fromString(idUser), idPrint, rate);
		}		
		
		repository.save(print);
		
		return rateRepository.save(ratePrint);
	}

	public boolean exists(UUID id){
		if(id != null){
			return repository.exists(id);
		}

		return false;
	}
}
