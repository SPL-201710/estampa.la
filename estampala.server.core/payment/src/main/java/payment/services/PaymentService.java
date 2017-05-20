package payment.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import commons.exceptions.EstampalaException;
import commons.util.DateTypeAdapter;
import commons.util.Endpoints;
import commons.util.EstampalaTools;
import payment.exceptions.PaymentNotFoundException;
import payment.exceptions.TooManyPaymentMethodsException;
import payment.models.Payment;
import payment.models.PaymentMethodPSE;
import payment.models.PaymentMethodPSERepository;
import payment.models.PaymentRepository;
import payment.pojos.PaymentCreator;
import commons.exceptions.CartNotFoundException;
import commons.exceptions.OwnerNotFoundException;
import commons.responses.SuccessResponse;
import commons.util.Endpoints;

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

	public String getInfoPayment(UUID id) {

		Payment payment = find(id);

		List<String> parameters = new ArrayList<>();
		parameters.add(payment.getShoppingcart().toString());

		GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateTypeAdapter());
        Gson gson = builder.create();

		JsonObject json = (JsonObject) gson.toJsonTree(payment);
		json.remove("shoppingcart");

		JsonObject jsonCart = (JsonObject) gson.toJsonTree(EstampalaTools.invokeGetRestServices(Endpoints.SHOPPING_CAR.getPath(), parameters, null, SuccessResponse.class));
		json.add("shoppingcart", jsonCart);

		return gson.toJson(json);
	}
	
	public void createPaymentMethods(PaymentCreator creator) throws TooManyPaymentMethodsException {
		
		if(creator.getPse_method() != null && creator.getCreditcard_method() != null && creator.getGiftcard_method() != null) {
			throw new TooManyPaymentMethodsException();
		}
	}
}
