package payment.pojos;

import java.sql.Date;
import java.util.UUID;

public class PaymentCreator {

	private UUID payment;
	private Date date;
	private UUID user;
	private UUID product;
	private double value;
	
	
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
	public UUID getUser() {
		return user;
	}
	public void setUser(UUID user) {
		this.user = user;
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
}
