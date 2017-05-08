package shoppingcart.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.exceptions.CartNotFoundException;
import commons.exceptions.OwnerNotFoundException;
import commons.responses.SuccessResponse;
import commons.util.Endpoints;
import commons.util.EstampalaTools;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import shoppingcart.models.ShoppingCart;
import shoppingcart.models.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository cartRepository;

	public ShoppingCartService() {

	}

	public ShoppingCart find(UUID id) {
		return cartRepository.findOne(id);
	}

	public Page<ShoppingCart> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return cartRepository.findAll(pageRequest);
	}

	public ShoppingCart saveShoppingCart(ShoppingCart cart) throws OwnerNotFoundException {
		if(cart != null) {
			
			UUID owner = cart.getOwner();
			List<String> pathParameters = new ArrayList<String>();			
			pathParameters.add(owner.toString());
			
			SuccessResponse res = EstampalaTools.invokeGetRestServices(Endpoints.USERS_EXIST, pathParameters, null, SuccessResponse.class);
			if (res == null || !res.isSuccess()){
				throw new OwnerNotFoundException(owner.toString());
			}
			
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
