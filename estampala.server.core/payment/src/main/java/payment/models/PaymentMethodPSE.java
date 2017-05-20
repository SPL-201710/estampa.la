package payment.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PaymentMethodPSE extends PaymentMethod{
	
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
	
	public PaymentMethodPSE (){

	}

	public PaymentMethodPSE(UUID id, String firtsName, String lastName, String phone, String email, String bank,
			String bankCode, String reference, String identificationType, String identification, Payment payment) {
		super();
		this.id = id;
		this.firtsName = firtsName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.bank = bank;
		this.bankCode = bankCode;
		this.reference = reference;
		this.identificationType = identificationType;
		this.identification = identification;
	}

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

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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
}
