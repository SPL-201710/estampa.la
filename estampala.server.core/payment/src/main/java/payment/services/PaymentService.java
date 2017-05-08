package payment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import commons.exceptions.CartNotFoundException;
import commons.exceptions.EstampalaException;
import commons.exceptions.OwnerNotFoundException;
import commons.responses.SuccessResponse;
import commons.util.Endpoints;
import commons.util.EstampalaTools;
import payment.models.Payment;
import payment.models.PaymentMethodPSE;
import payment.models.PaymentMethodPSERepository;
import payment.models.PaymentRepository;
import payment.pojos.PaymentCreator;

/**
 *
 * @author akane
 *
 */
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentMethodPSERepository pseRepository;

	public PaymentService() {

	}

	public Payment find(UUID id) {
		return paymentRepository.findOne(id);
	}

	public Page<Payment> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return paymentRepository.findAll(pageRequest);
	}

	public Payment save(PaymentCreator item) throws EstampalaException {
		if (item != null) {
			
			UUID owner = item.getOwner();
			List<String> pathParameters = new ArrayList<String>();			
			pathParameters.add(owner.toString());
			
			SuccessResponse res = EstampalaTools.invokeGetRestServices(Endpoints.USERS_EXIST, pathParameters, null, SuccessResponse.class);
			if (res == null || !res.isSuccess()){
				throw new OwnerNotFoundException(owner.toString());
			}
			
			UUID shoppingcart = item.getShoppingcart();
			pathParameters = new ArrayList<String>();			
			pathParameters.add(shoppingcart.toString());
			
			res = EstampalaTools.invokeGetRestServices(Endpoints.SHOPPING_CAR_EXIST, pathParameters, null, SuccessResponse.class);
			if (res == null || !res.isSuccess()){
				throw new CartNotFoundException(shoppingcart);
			}
			
			Payment payment = new Payment(UUID.randomUUID(), item.getDate(), item.getTotal(), item.getOwner(), item.getShoppingcart());
			payment = paymentRepository.save(payment);

			PaymentMethodPSE pse = item.getPse_method();
			pse.setPayment(payment);

			pseRepository.save(pse);

			return paymentRepository.save(payment);
		}
		return null;
	}

	public Payment update(PaymentCreator item) throws EstampalaException {
		if (item != null) {
			Payment payment = new Payment(item.getPayment(), item.getDate(), item.getTotal(), item.getOwner(), item.getShoppingcart());
			return paymentRepository.save(payment);
		}
		return null;
	}

	public void delete(UUID id) {
		if(id != null){
			paymentRepository.delete(id);
		}
	}

	public boolean exists(UUID id) {
		if(id != null){
			return paymentRepository.exists(id);
		}
		return false;
	}
}
