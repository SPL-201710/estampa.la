package shoppingcart.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppingcart.exceptions.CartNotFoundException;
import shoppingcart.models.ShoppingCart;
import shoppingcart.models.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository cartRepository;
	
	public ShoppingCartService() {
		
	}
	
	public ShoppingCart saveShoppingCart(ShoppingCart cart) {
		if(cart != null) {
			return cartRepository.save(cart);
		}
		
		return null;
	}
	
	public ShoppingCart findById(UUID id) throws CartNotFoundException {
		if(id != null ) {
			
			if(cartRepository.findOne(id) == null) {
				throw new CartNotFoundException(id);
			}
			
			return cartRepository.findOne(id);
		}
		return null;
	}
	
	public void deleteShoppingCart(UUID id) throws CartNotFoundException {
		if(!cartRepository.exists(id)) {
			throw new CartNotFoundException(id);
		}
		
		cartRepository.delete(id);
	}
	
	public boolean exists(UUID id) {
		return cartRepository.exists(id);
	}
}
