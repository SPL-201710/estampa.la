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
import shoppingcart.exceptions.TransactionNotFoundException;
import shoppingcart.models.ShoppingTransaction;
import shoppingcart.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class ShoppingTransactionController extends EstampalaController{
	
	@Autowired
	private TransactionService txService;
	
	@RequestMapping(value = "/create",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingTransaction> createTransaction(@RequestBody ShoppingTransaction transaction) {
		
		return new ResponseEntity<ShoppingTransaction>(txService.saveTransaction(transaction), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingTransaction> updateShoppingTransaction(@RequestBody ShoppingTransaction transaction) throws TransactionNotFoundException {
		
		if(transaction.getId() == null) {
			throw new TransactionNotFoundException(null);
		}
		
		if(!txService.exists(transaction.getId())) {
			throw new TransactionNotFoundException(transaction.getId());
		}
		
		transaction = txService.saveTransaction(transaction);
		
		return new ResponseEntity<ShoppingTransaction>(transaction, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingTransaction> deleteShoppingTransaction(@RequestBody ShoppingTransaction transaction) throws TransactionNotFoundException {
		
		if(transaction.getId() == null) {
			throw new TransactionNotFoundException(null);
		}
		
		if(!txService.exists(transaction.getId())) {
			throw new TransactionNotFoundException(transaction.getId());
		}
		
		transaction = txService.saveTransaction(transaction);
		
		return new ResponseEntity<ShoppingTransaction>(transaction, HttpStatus.OK);
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
