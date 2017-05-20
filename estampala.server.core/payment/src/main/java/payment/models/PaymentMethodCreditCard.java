package payment.models;

import javax.persistence.Entity;

@Entity
public class PaymentMethodCreditCard extends PaymentMethod{

	private String cardNumber;
	private String cvc;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
}
