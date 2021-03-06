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

import catalog.exceptions.ThemeAlreadyExistsException;
import catalog.exceptions.ThemeNotFoundException;
import catalog.models.theme.Theme;
import catalog.services.ThemeService;
import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/themes")
public class ThemeController extends EstampalaController {

	@Autowired
	private ThemeService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Theme>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<Theme>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> get(@PathVariable UUID id) throws ThemeNotFoundException {
		if(!service.exists(id)) {
			throw new ThemeNotFoundException();
		}

		return new ResponseEntity<Theme>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> create(@RequestBody(required=false) Theme element) throws ThemeAlreadyExistsException {
		if(service.exists(element.getId())) {
			throw new ThemeAlreadyExistsException();
		}

		return new ResponseEntity<Theme>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> updatePatch(@PathVariable UUID id, @RequestBody Theme element) throws ThemeNotFoundException {
		if(!service.exists(id)) {
			throw new ThemeNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<Theme>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Theme> update(@PathVariable UUID id, @RequestBody Theme element) throws ThemeNotFoundException {
		if(!service.exists(id)) {
			throw new ThemeNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<Theme>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ThemeNotFoundException {
		if(!service.exists(id)) {
			throw new ThemeNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The theme was successfully deleted");

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
