package payment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import commons.exceptions.EstampalaException;
import payment.models.Payment;
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
	private PaymentRepository repository;

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
		if (item != null) {
			Payment payment = new Payment(UUID.randomUUID(), item.getDate(), item.getValue(), item.getUser_id(), item.getProduct());
			return repository.save(payment);
		}
		return null;
	}

	public Payment update(PaymentCreator item) throws EstampalaException {
		if (item != null) {
			Payment payment = new Payment(item.getPayment(), item.getDate(), item.getValue(), item.getUser_id(), item.getProduct());
			return repository.save(payment);
		}
		return null;
	}

	public void delete(UUID id) {
		if(id != null){
			repository.delete(id);
		}
	}

	public boolean exists(UUID id) {
		if(id != null){
			return repository.exists(id);
		}
		return false;
	}
}
