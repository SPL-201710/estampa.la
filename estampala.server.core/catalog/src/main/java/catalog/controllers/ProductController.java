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

import catalog.exceptions.ProductNotFoundException;
import catalog.models.product.Product;
import catalog.pojos.ProductCreator;
import catalog.services.ProductService;
import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;

@RestController
@RequestMapping("/products")
public class ProductController extends EstampalaController {

	@Autowired
	private ProductService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<Product>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> get(@PathVariable UUID id) throws ProductNotFoundException {
		if(!service.exists(id)) {
			throw new ProductNotFoundException();
		}

		return new ResponseEntity<Product>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> create(@RequestBody(required=false) ProductCreator element) throws EstampalaException {
		return new ResponseEntity<Product>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody ProductCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new ProductNotFoundException();
		}

		element.setProduct(id);
		return new ResponseEntity<Product>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws ProductNotFoundException {
		if(!service.exists(id)) {
			throw new ProductNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The Product was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
