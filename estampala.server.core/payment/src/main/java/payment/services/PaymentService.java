package payment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.exceptions.ProductNotFoundException;
import catalog.models.product.Product;
import commons.exceptions.EstampalaException;
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
			Payment payment = new Payment(UUID.randomUUID(), item.getDate(), item.getValue(), item.getUser_id(), item.getProduct());
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
			Payment payment = new Payment(item.getPayment(), item.getDate(), item.getValue(), item.getUser_id(), item.getProduct());
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
