package catalog.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import catalog.exceptions.ThemeAlreadyExistsException;
import catalog.exceptions.ThemeNotFoundException;
import catalog.models.theme.Theme;
import catalog.services.ThemeService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/api/v1/Themes")
public class ThemeController extends EstampalaController {
	
	@Autowired
	private ThemeService service;

	@RequestMapping(value = "/page={page}&page_size={pageSize}",method = RequestMethod.GET)
	public ResponseEntity<Page<Theme>> getAll(@PathVariable int page, @PathVariable int pageSize) {		
		return new ResponseEntity<Page<Theme>>(service.findAll(page, pageSize), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> get(@PathVariable UUID id) throws ThemeNotFoundException {
		if(!service.exists(id)) {
			throw new ThemeNotFoundException();			
		}
		
		return new ResponseEntity<Theme>(service.find(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> create(@RequestBody Theme element) throws ThemeAlreadyExistsException {		
		if(service.exists(element.getId())) {
			throw new ThemeAlreadyExistsException();
		}
				
		return new ResponseEntity<Theme>(element, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> update(@PathVariable UUID id, @RequestBody Theme element) throws ThemeNotFoundException {		
		if(!service.exists(element.getId())) {
			throw new ThemeNotFoundException();
		}		
		
		return new ResponseEntity<Theme>(service.save(element), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ThemeNotFoundException {
		
		if(service.exists(id)) {
			throw new ThemeNotFoundException();
		}
		
		service.delete(id);
		
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The theme was successfully deleted");
		
		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}		
}
