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
	private String identificationType;

	@Column(nullable = false)
	private String identification;
	
	@Column(nullable = false)
	private String number;
	
	@Column(nullable = false)
	private String cvc;
	
	public String getFirtsName() {
		return firtsName;
	}
	public void setFirtsName(String firtsName) {
		this.firtsName = firtsName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
}
