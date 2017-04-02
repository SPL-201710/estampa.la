package payment.pojos;

import java.sql.Date;
import java.util.UUID;

import payment.models.PaymentMethodPSE;

public class PaymentCreator {

	private UUID payment;
	private Date date;
	private UUID user_id;
	private UUID product;
	private double value;
	private PaymentMethodPSE pse_method;

	public UUID getPayment() {
		return payment;
	}
	public void setPayment(UUID payment) {
		this.payment = payment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UUID getUser_id() {
		return user_id;
	}
	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	public UUID getProduct() {
		return product;
	}
	public void setProduct(UUID product) {
		this.product = product;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public PaymentMethodPSE getPse_method() {
		return pse_method;
	}
	public void setPse_method(PaymentMethodPSE pse_method) {
		this.pse_method = pse_method;
	}	
}
