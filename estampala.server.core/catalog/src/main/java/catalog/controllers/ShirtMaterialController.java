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

import catalog.exceptions.ShirtMaterialAlreadyExistsException;
import catalog.exceptions.ShirtMaterialNotFoundException;
import catalog.models.shirt.ShirtMaterial;
import catalog.services.ShirtMaterialService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/api/v1/shirtMaterials")
public class ShirtMaterialController extends EstampalaController {
	
	@Autowired
	private ShirtMaterialService service;

	@RequestMapping(value = "/page={page}&page_size={pageSize}",method = RequestMethod.GET)
	public ResponseEntity<Page<ShirtMaterial>> getAll(@PathVariable int page, @PathVariable int pageSize) {		
		return new ResponseEntity<Page<ShirtMaterial>>(service.findAll(page, pageSize), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtMaterial> get(@PathVariable UUID id) throws ShirtMaterialNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtMaterialNotFoundException();			
		}
		
		return new ResponseEntity<ShirtMaterial>(service.find(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtMaterial> create(@RequestBody ShirtMaterial element) throws ShirtMaterialAlreadyExistsException {		
		if(service.exists(element.getId())) {
			throw new ShirtMaterialAlreadyExistsException();
		}
				
		return new ResponseEntity<ShirtMaterial>(element, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtMaterial> update(@PathVariable UUID id, @RequestBody ShirtMaterial element) throws ShirtMaterialNotFoundException {		
		if(!service.exists(element.getId())) {
			throw new ShirtMaterialNotFoundException();
		}		
		
		return new ResponseEntity<ShirtMaterial>(service.save(element), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ShirtMaterialNotFoundException {
		
		if(service.exists(id)) {
			throw new ShirtMaterialNotFoundException();
		}
		
		service.delete(id);
		
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The material was successfully deleted");
		
		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}		
}