package payment.pojos;

import java.sql.Date;
import java.util.UUID;

import payment.models.PaymentMethodCreditCard;
import payment.models.PaymentMethodGiftCard;
import payment.models.PaymentMethodPSE;

public class PaymentCreator {

	private UUID payment;
	private Date date;
	private UUID owner;
	private UUID shoppingcart;
	private double total;
	private PaymentMethodPSE pse_method;
	private UUID giftcard;
	private PaymentMethodCreditCard credit_method;

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
	public UUID getOwner() {
		return owner;
	}
	public void setOwner(UUID owner) {
		this.owner = owner;
	}
	public UUID getShoppingcart() {
		return shoppingcart;
	}
	public void setShoppingcart(UUID shoppingcart) {
		this.shoppingcart = shoppingcart;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public PaymentMethodPSE getPse_method() {
		return pse_method;
	}
	public void setPse_method(PaymentMethodPSE pse_method) {
		this.pse_method = pse_method;
	}
	
	public UUID getGiftcard() {
		return giftcard;
	}
	public void setGiftcard(UUID giftcard) {
		this.giftcard = giftcard;
	}
	public PaymentMethodCreditCard getCredit_method() {
		return credit_method;
	}
	public void setCredit_method(PaymentMethodCreditCard credit_method) {
		this.credit_method = credit_method;
	}
}
