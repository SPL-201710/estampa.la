package shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import shoppingcart.exceptions.CartNotFoundException;
import shoppingcart.models.ShoppingCart;
import shoppingcart.services.ShoppingCartService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController extends EstampalaController {
	
	@Autowired
	private ShoppingCartService cartService;
	
	public ShoppingCartController() {
		
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart cart) {
		
		return new ResponseEntity<ShoppingCart>(cartService.saveShoppingCart(cart), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCart cart) throws CartNotFoundException {
		
		if(cart.getId() == null) {
			throw new CartNotFoundException(null);
		}
		
		if(!cartService.exists(cart.getId())) {
			throw new CartNotFoundException(cart.getId());
		}
		
		cart = cartService.saveShoppingCart(cart);
		
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> deleteShoppingCart(@RequestBody ShoppingCart cart) throws CartNotFoundException {
		
		if(cart.getId() == null) {
			throw new CartNotFoundException(null);
		}
		
		if(!cartService.exists(cart.getId())) {
			throw new CartNotFoundException(cart.getId());
		}
		
		cart = cartService.saveShoppingCart(cart);
		
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
	}
}
