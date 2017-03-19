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

import catalog.exceptions.PrintAlreadyExistsException;
import catalog.exceptions.PrintNotFoundException;
import catalog.models.print.Print;
import catalog.services.PrintService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/api/v1/prints")
public class PrintController extends EstampalaController {
	
	@Autowired
	private PrintService service;

	@RequestMapping(value = "/page={page}&page_size={pageSize}",method = RequestMethod.GET)
	public ResponseEntity<Page<Print>> getAll(@PathVariable int page, @PathVariable int pageSize) {		
		return new ResponseEntity<Page<Print>>(service.findAll(page, pageSize), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> get(@PathVariable UUID id) throws PrintNotFoundException {
		if(!service.exists(id)) {
			throw new PrintNotFoundException();			
		}
		
		return new ResponseEntity<Print>(service.find(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> create(@RequestBody Print print) throws PrintAlreadyExistsException {		
		if(service.exists(print.getId())) {
			throw new PrintAlreadyExistsException();
		}
				
		return new ResponseEntity<Print>(print, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> update(@PathVariable UUID id, @RequestBody Print print) throws PrintNotFoundException {		
		if(!service.exists(print.getId())) {
			throw new PrintNotFoundException();
		}		
		
		return new ResponseEntity<Print>(service.save(print), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws PrintNotFoundException {
		
		if(service.exists(id)) {
			throw new PrintNotFoundException();
		}
		
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The print was successfully deleted");
		
		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}		
}
