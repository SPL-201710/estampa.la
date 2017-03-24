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

import catalog.exceptions.ShirtStyleAlreadyExistsException;
import catalog.exceptions.ShirtStyleNotFoundException;
import catalog.models.shirt.ShirtStyle;
import catalog.services.ShirtStyleService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/shirtStyles")
public class ShirtStyleController extends EstampalaController {

	@Autowired
	private ShirtStyleService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<ShirtStyle>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<ShirtStyle>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtStyle> get(@PathVariable UUID id) throws ShirtStyleNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtStyleNotFoundException();
		}

		return new ResponseEntity<ShirtStyle>(service.find(id), HttpStatus.OK);
	}

	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtStyle> create(@RequestBody(required=false) ShirtStyle element) throws ShirtStyleAlreadyExistsException {
		if(service.exists(element.getId())) {
			throw new ShirtStyleAlreadyExistsException();
		}

		return new ResponseEntity<ShirtStyle>(service.save(element), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtStyle> update(@PathVariable UUID id, @RequestBody ShirtStyle element) throws ShirtStyleNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtStyleNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtStyle>(service.save(element), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ShirtStyleNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtStyleNotFoundException();
		}

		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The size was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
