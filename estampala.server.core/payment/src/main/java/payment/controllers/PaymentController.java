package payment.controllers;

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

import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;
import payment.exceptions.PaymentNotFoundException;
import payment.models.Payment;
import payment.pojos.PaymentCreator;
import payment.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController extends EstampalaController {

	@Autowired
	private PaymentService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Payment>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<Payment>>(service.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> get(@PathVariable UUID id) throws PaymentNotFoundException {
		if(!service.exists(id)) {
			throw new PaymentNotFoundException();
		}
		return new ResponseEntity<Payment>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> create(@RequestBody(required=false) PaymentCreator element) throws EstampalaException {
		return new ResponseEntity<Payment>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> update(@PathVariable UUID id, @RequestBody PaymentCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new PaymentNotFoundException();
		}
		element.setPayment(id);
		return new ResponseEntity<Payment>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> updatePatch(@PathVariable UUID id, @RequestBody PaymentCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new PaymentNotFoundException();
		}
		element.setPayment(id);
		return new ResponseEntity<Payment>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping
	
	(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws PaymentNotFoundException {
		if(!service.exists(id)) {
			throw new PaymentNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The Payment was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
