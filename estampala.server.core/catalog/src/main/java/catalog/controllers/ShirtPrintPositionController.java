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

import catalog.exceptions.ShirtPrintPositionAlreadyExistsException;
import catalog.exceptions.ShirtPrintPositionNotFoundException;
import catalog.exceptions.ShirtSizeNotFoundException;
import catalog.models.shirt.ShirtPrintPosition;
import catalog.services.ShirtPrintPositionService;
import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/shirtprintpositions")
public class ShirtPrintPositionController extends EstampalaController {

	@Autowired
	private ShirtPrintPositionService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<ShirtPrintPosition>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<ShirtPrintPosition>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtPrintPosition> get(@PathVariable UUID id) throws ShirtPrintPositionNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtPrintPositionNotFoundException();
		}

		return new ResponseEntity<ShirtPrintPosition>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtPrintPosition> create(@RequestBody(required=false) ShirtPrintPosition element) throws ShirtPrintPositionAlreadyExistsException {
		if(service.exists(element.getId())) {
			throw new ShirtPrintPositionAlreadyExistsException();
		}

		return new ResponseEntity<ShirtPrintPosition>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtPrintPosition> update(@PathVariable UUID id, @RequestBody ShirtPrintPosition element) throws ShirtPrintPositionNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtPrintPositionNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtPrintPosition>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShirtPrintPosition> updatePatch(@PathVariable UUID id, @RequestBody ShirtPrintPosition element) throws ShirtPrintPositionNotFoundException {
		if(!service.exists(id)) {
			throw new ShirtPrintPositionNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<ShirtPrintPosition>(service.save(element), HttpStatus.OK);
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
		response.setMessage("The print position was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/exist/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> exist(@PathVariable UUID id) throws EstampalaException {
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(service.exists(id));
		response.setMessage("Look success attribute");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
