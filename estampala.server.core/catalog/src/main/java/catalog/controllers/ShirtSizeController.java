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

import catalog.exceptions.ShirtSizeAlreadyExistsException;
import catalog.exceptions.ShirtSizeNotFoundException;
import catalog.models.shirt.ShirtSize;
import catalog.services.ShirtSizeService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/shirtsizes")
public class ShirtSizeController extends EstampalaController {

	@Autowired
	private ShirtSizeService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<ShirtSize>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<ShirtSize>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtSize> get(@PathVariable UUID id) throws ShirtSizeNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtSizeNotFoundException();
		}

		return new ResponseEntity<ShirtSize>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtSize> create(@RequestBody(required=false) ShirtSize element) throws ShirtSizeAlreadyExistsException {
		if(service.exists(element.getId())) {
			throw new ShirtSizeAlreadyExistsException();
		}

		return new ResponseEntity<ShirtSize>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtSize> update(@PathVariable UUID id, @RequestBody ShirtSize element) throws ShirtSizeNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtSizeNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtSize>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtSize> updatePatch(@PathVariable UUID id, @RequestBody ShirtSize element) throws ShirtSizeNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtSizeNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtSize>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ShirtSizeNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtSizeNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The size was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
