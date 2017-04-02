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

import catalog.exceptions.ShirtNotFoundException;
import catalog.models.shirt.Shirt;
import catalog.pojos.ShirtCreator;
import catalog.services.ShirtService;
import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/shirts")
public class ShirtController extends EstampalaController {

	@Autowired
	private ShirtService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Shirt>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<Shirt>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shirt> get(@PathVariable UUID id) throws ShirtNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtNotFoundException();
		}

		return new ResponseEntity<Shirt>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shirt> create(@RequestBody(required=false) ShirtCreator element) throws EstampalaException {
		return new ResponseEntity<Shirt>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shirt> update(@PathVariable UUID id, @RequestBody ShirtCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new ShirtNotFoundException();
		}

		element.setShirt(id);
		return new ResponseEntity<Shirt>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ShirtNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The shirt was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
