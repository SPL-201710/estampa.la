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

import catalog.exceptions.PrintFontAlreadyExistsException;
import catalog.exceptions.PrintFontNotFoundException;
import catalog.models.print.PrintFont;
import catalog.services.PrintFontsService;
import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/printFonts")
public class PrintFontsController extends EstampalaController {

	@Autowired
	private PrintFontsService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<PrintFont>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<PrintFont>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrintFont> get(@PathVariable UUID id) throws PrintFontNotFoundException {
		if(!service.exists(id)) {
			throw new PrintFontNotFoundException();
		}

		return new ResponseEntity<PrintFont>(service.find(id), HttpStatus.OK);
	}

	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrintFont> create(@RequestBody(required=false) PrintFont element) throws PrintFontAlreadyExistsException {
		if(service.exists(element.getId())) {
			throw new PrintFontAlreadyExistsException();
		}

		return new ResponseEntity<PrintFont>(service.save(element), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrintFont> update(@PathVariable UUID id, @RequestBody PrintFont element) throws PrintFontNotFoundException {
		if(!service.exists(id)) {
			throw new PrintFontNotFoundException();
		}

		element.setId(id);
		return new ResponseEntity<PrintFont>(service.save(element), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws PrintFontNotFoundException {
		if(!service.exists(id)) {
			throw new PrintFontNotFoundException();
		}

		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The font was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
