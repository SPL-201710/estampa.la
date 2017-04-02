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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import catalog.exceptions.ShirtColorAlreadyExistsException;
import catalog.exceptions.ShirtColorNotFoundException;
import catalog.models.shirt.ShirtColor;
import catalog.services.ShirtColorService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/shirtcolors")
public class ShirtColorController extends EstampalaController {

	@Autowired
	private ShirtColorService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<ShirtColor>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<ShirtColor>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtColor> get(@PathVariable UUID id) throws ShirtColorNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtColorNotFoundException();
		}

		return new ResponseEntity<ShirtColor>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtColor> create(@RequestBody(required=false) ShirtColor element) throws ShirtColorAlreadyExistsException {
		if(service.exists(element.getId())) {
			throw new ShirtColorAlreadyExistsException();
		}

		return new ResponseEntity<ShirtColor>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtColor> update(@PathVariable UUID id, @RequestBody(required=false) ShirtColor element) throws ShirtColorNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtColorNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtColor>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtColor> updatePatch(@PathVariable UUID id, @RequestBody(required=false) ShirtColor element) throws ShirtColorNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtColorNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtColor>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ShirtColorNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtColorNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The color was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
