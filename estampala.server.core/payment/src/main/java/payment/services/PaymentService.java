package payment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import commons.exceptions.EstampalaException;
import commons.exceptions.ResourceNotFoundException;
import commons.util.FeaturesFlag;
import payment.exceptions.GiftCardNotEnoughBalanceException;
import payment.exceptions.GiftCardNotFoundException;
import payment.exceptions.TooManyPaymentMethodsException;
import payment.models.GiftCard;
import payment.models.Payment;
import payment.models.PaymentMethodCreditCard;
import payment.models.PaymentMethodGiftCard;
import payment.models.PaymentMethodPSE;
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
	private GiftCardService giftCardService;

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

			// UUID owner = item.getOwner();
			// List<String> pathParameters = new ArrayList<String>();
			// pathParameters.add(owner.toString());
			//
			// SuccessResponse res = EstampalaTools.invokeGetRestServices(Endpoints.USERS_EXIST, pathParameters, null, SuccessResponse.class);
			// if (res == null || !res.isSuccess()){
			// 	throw new OwnerNotFoundException(owner.toString());
			// }
			//
			//  UUID shoppingcart = item.getShoppingcart();
			//  pathParameters = new ArrayList<String>();
			//  pathParameters.add(shoppingcart.toString());
			//
			//  res = EstampalaTools.invokeGetRestServices(Endpoints.SHOPPING_CAR_EXIST, pathParameters, null, SuccessResponse.class);
			//  if (res == null || !res.isSuccess()){
			//  	throw new CartNotFoundException(shoppingcart);
			//  }

			Payment payment = new Payment(UUID.randomUUID(), item.getDate(), item.getTotal(), item.getOwner(), item.getShoppingcart());
			
			return createPaymentMethods(payment, item);
		}
		return null;
	}

	public Payment update(PaymentCreator item) throws EstampalaException {
		if (item != null) {
			Payment payment = new Payment(item.getPayment(), item.getDate(), item.getTotal(), item.getOwner(), item.getShoppingcart());
			return createPaymentMethods(payment, item);
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
	
	public Payment createPaymentMethods(Payment payment, PaymentCreator creator) throws TooManyPaymentMethodsException, GiftCardNotFoundException, GiftCardNotEnoughBalanceException, ResourceNotFoundException {
		
		if(creator.getPse_method() != null) {
			PaymentMethodPSE pseMethod = creator.getPse_method();
			
			payment.setPaymentMethod(pseMethod);
			paymentRepository.save(payment);
			
		}

		else if(creator.getCredit_method() != null) {
			
			if(!FeaturesFlag.TARJETA_CREDITO.isActive()) {
				throw new ResourceNotFoundException("tarjeta_credito");
			}
			
			PaymentMethodCreditCard creditCardMethod = creator.getCredit_method();
			
			payment.setPaymentMethod(creditCardMethod);
			paymentRepository.save(payment);
		}

		else if(creator.getGiftcard() != null) {
			
			if(!FeaturesFlag.TARJETA_REGALO.isActive()) {
				throw new ResourceNotFoundException("tarjeta_regalo");
			}
			
			if(!giftCardService.exists(creator.getGiftcard())) {
				throw new GiftCardNotFoundException();
			}

			GiftCard card = giftCardService.find(creator.getGiftcard());

			giftCardService.payGiftCard(card.getId(), creator.getTotal());
			PaymentMethodGiftCard payMethodCard = new PaymentMethodGiftCard();
			payMethodCard.setGiftCard(card);
			
			payment.setPaymentMethod(payMethodCard);
			paymentRepository.save(payment);
		}
		
		return payment;
	}
}
