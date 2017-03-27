package payment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.exceptions.ProductNotFoundException;
import catalog.models.product.Product;
import catalog.services.ProductService;
import commons.exceptions.EstampalaException;
import payment.models.Payment;
import payment.models.PaymentRepository;
import payment.pojos.PaymentCreator;
import users.exceptions.UserNotFoundException;
import users.models.User;
import users.services.UserService;

/**
 *
 * @author akane
 *
 */
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;	

	public PaymentService() {

	}

	public Payment find(UUID id) {
		return repository.findOne(id);
	}

	public Page<Payment> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}

	public Payment save(PaymentCreator item) throws EstampalaException {
		if (item != null){
			User user = userService.findUserById(item.getUser());
			if (user == null){
				throw new UserNotFoundException(item.getUser());
			}
			
			Product product = productService.find(item.getProduct());
			if (product == null){
				throw new ProductNotFoundException();
			}
			
			Payment payment = new Payment(UUID.randomUUID(), item.getDate(), item.getValue(), user, product);
					
			return repository.save(payment);
		}
		return null;
	}

	public Payment update(PaymentCreator item) throws EstampalaException {
		if (item != null){
			User user = userService.findUserById(item.getUser());
			if (user == null){
				throw new UserNotFoundException(item.getUser());
			}
			
			Product product = productService.find(item.getProduct());
			if (product == null){
				throw new ProductNotFoundException();
			}
			
			Payment payment = new Payment(item.getPayment(), item.getDate(), item.getValue(), user, product);
					
			return repository.save(payment);
		}
		return null;
	}

	public void delete(UUID id){
		if(id != null){
			repository.delete(id);
		}
	}

	public boolean exists(UUID id){
		if(id != null){
			return repository.exists(id);
		}

		return false;
	}
}
