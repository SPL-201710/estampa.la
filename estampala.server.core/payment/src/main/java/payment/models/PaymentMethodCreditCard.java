package payment.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PaymentMethodCreditCard extends PaymentMethod{
	
	@Column(nullable = true)
	private String firtsName;

	@Column(nullable = true)
	private String lastName;

	@Column(nullable = true)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String bank;

	@Column(nullable = false)
	private String bankCode;

	@Column(nullable = false)
	private String reference;

	@Column(nullable = false)
	private String identificationType;

	@Column(nullable = false)
	private String identification;
	
	@Column(nullable = false)
	private String cardNumber;
	
	@Column(nullable = false)
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
