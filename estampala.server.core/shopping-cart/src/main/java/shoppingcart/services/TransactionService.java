package shoppingcart.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppingcart.exceptions.TransactionNotFoundException;
import shoppingcart.models.ShoppingTransaction;
import shoppingcart.models.ShoppingTransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private ShoppingTransactionRepository txRepository;
	
	public TransactionService() {

	}
	
	public ShoppingTransaction saveTransaction(ShoppingTransaction transaction) {
		if(transaction != null) {
			return txRepository.save(transaction);
		}
		return null;
	}
	
	public ShoppingTransaction findById(UUID id) throws TransactionNotFoundException {
		if(id != null ) {
			
			if(txRepository.findOne(id) == null) {
				throw new TransactionNotFoundException(id);
			}
			
			return txRepository.findOne(id);
		}
		return null;
	}
	
	public void deleteShoppingCart(UUID id) throws TransactionNotFoundException {
		if(!txRepository.exists(id)) {
			throw new TransactionNotFoundException(id);
		}
		
		txRepository.delete(id);
	}
	
	public boolean exists(UUID id) {
		return txRepository.exists(id);
	}
	
	public List<ShoppingTransaction>findAllByUserId(UUID id) {
		return txRepository.findAllByUserId(id);
	}
}
