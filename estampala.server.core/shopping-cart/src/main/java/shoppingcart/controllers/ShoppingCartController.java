package shoppingcart.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;
import shoppingcart.exceptions.CartNotFoundException;
import shoppingcart.models.ShoppingCart;
import shoppingcart.services.ShoppingCartService;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController extends EstampalaController {

	@Autowired
	private ShoppingCartService cartService;

	public ShoppingCartController() {

	}

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<ShoppingCart>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
			return new ResponseEntity<Page<ShoppingCart>>(cartService.findAll(page, pageSize), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> get(@PathVariable UUID id) throws CartNotFoundException {
		if(!cartService.exists(id)) {
			throw new CartNotFoundException(id);
		}

		return new ResponseEntity<ShoppingCart>(cartService.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart cart) {

		return new ResponseEntity<ShoppingCart>(cartService.saveShoppingCart(cart), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable UUID id, @RequestBody ShoppingCart cart) throws CartNotFoundException {
		if(!cartService.exists(id)) {
			throw new CartNotFoundException(id);
		}
		cart.setId(id);
		cart = cartService.saveShoppingCart(cart);

		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> updateShoppingCartPatch(@PathVariable UUID id, @RequestBody ShoppingCart cart) throws CartNotFoundException {
		if(!cartService.exists(id)) {
			throw new CartNotFoundException(id);
		}
		cart.setId(id);
		cart = cartService.saveShoppingCart(cart);

		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteShoppingCart(@PathVariable UUID id) throws CartNotFoundException {
		if(!cartService.exists(id)) {
			throw new CartNotFoundException(id);
		}
		cartService.deleteShoppingCart(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The shoppingcart was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
