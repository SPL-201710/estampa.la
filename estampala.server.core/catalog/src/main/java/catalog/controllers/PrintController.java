package catalog.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import catalog.exceptions.PrintAlreadyExistsException;
import catalog.exceptions.PrintNotFoundException;
import catalog.models.print.Print;
import catalog.models.print.RatePrint;
import catalog.pojos.PrintCreator;
import catalog.pojos.PrintRate;
import catalog.services.PrintService;
import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;


@RestController
@RequestMapping("/prints")
public class PrintController extends EstampalaController {

	@Autowired(required=true)
	private HttpServletRequest request;

	@Autowired
	private PrintService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Print>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page,
											@RequestParam(value="page_size", defaultValue="10", required = false) int pageSize,
											@RequestParam(value="popularity", defaultValue="asc", required = false) String popularity,
											@And({	@Spec(path = "theme.name", params={"theme"}, spec = Like.class),
													@Spec(path = "ownerUsername", params={"owner"}, spec = Equal.class),
													@Spec(path = "active", params={"active"}, spec = Equal.class),
													@Spec(path = "rating", spec = Equal.class)}) Specification<Print> spec) {


		return new ResponseEntity<Page<Print>>(service.findAll(page, pageSize, popularity, spec), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> get(@PathVariable UUID id) throws PrintNotFoundException {
		if(!service.exists(id)) {
			throw new PrintNotFoundException();
		}

		return new ResponseEntity<Print>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> create(@RequestBody(required=false) PrintCreator element) throws EstampalaException {
		if(service.exists(element.getPrint())) {
			throw new PrintAlreadyExistsException();
		}

		return new ResponseEntity<Print>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> update(@PathVariable UUID id, @RequestBody PrintCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new PrintNotFoundException();
		}

		element.setPrint(id);
		return new ResponseEntity<Print>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Print> updatePatch(@PathVariable UUID id, @RequestBody PrintCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new PrintNotFoundException();
		}

		element.setPrint(id);
		return new ResponseEntity<Print>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws PrintNotFoundException {
		if(!service.exists(id)) {
			throw new PrintNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The print was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}/rate", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatePrint> rate(@PathVariable UUID id, @RequestBody PrintRate rate) throws EstampalaException {
		
//		String idUser = (String)request.getAttribute("idUser");
		return new ResponseEntity<RatePrint>(service.rate(id, rate), HttpStatus.OK);
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
