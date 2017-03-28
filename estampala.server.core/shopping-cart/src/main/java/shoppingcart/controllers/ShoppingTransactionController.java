package shoppingcart.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;
import shoppingcart.exceptions.TransactionNotFoundException;
import shoppingcart.models.ShoppingTransaction;
import shoppingcart.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class ShoppingTransactionController extends EstampalaController{

	@Autowired
	private TransactionService txService;

	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingTransaction> createTransaction(@RequestBody ShoppingTransaction transaction) {

		return new ResponseEntity<ShoppingTransaction>(txService.saveTransaction(transaction), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingTransaction> updateShoppingTransaction(@PathVariable UUID id, @RequestBody ShoppingTransaction transaction) throws TransactionNotFoundException {

		if(!txService.exists(id)) {
			throw new TransactionNotFoundException(transaction.getId());
		}

		transaction.setId(id);
		transaction = txService.saveTransaction(transaction);

		return new ResponseEntity<ShoppingTransaction>(transaction, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteShoppingTransaction(@PathVariable UUID id) throws TransactionNotFoundException {

		if(!txService.exists(id)) {
			throw new TransactionNotFoundException(id);
		}

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The shoppingcart was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShoppingTransaction>> findAllByUserId(@PathVariable UUID id) throws TransactionNotFoundException {

		if(id == null) {
			throw new TransactionNotFoundException(null);
		}

		List<ShoppingTransaction> transactions = txService.findAllByUserId(id);

		return new ResponseEntity<List<ShoppingTransaction>>(transactions, HttpStatus.OK);
	}
}
