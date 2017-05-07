package payment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import commons.exceptions.EstampalaException;
import commons.util.EstampalaTools;
import payment.exceptions.PaymentNotFoundException;
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
	
	private final RestTemplate restTemplate;

	public PaymentService() {
		restTemplate = new RestTemplate();
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
	
	public void getInfoPayment(UUID id) throws PaymentNotFoundException {
		
		Payment payment = find(id);
		
		if(payment == null) {
			throw new PaymentNotFoundException();
		}
		
		restTemplate.getForEntity("http://localhost:8082/api/v1/carts/" + payment.getShoppingcart(), null);
	}
	
}
