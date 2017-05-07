package payment.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class GiftCardMethodPSE {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@OneToOne
	private GiftCard giftCard;

	@Column(nullable = true)
	private String firstName;

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
	private Date date;
	
	public GiftCardMethodPSE() {
		
	}

	public GiftCardMethodPSE(UUID id, GiftCard giftCard, String firtsName, String lastName, String phone, String email,
			String bank, String bankCode, String reference, String identificationType, String identification,
			Date date) {
		super();
		this.id = id;
		this.giftCard = giftCard;
		this.firstName = firtsName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.bank = bank;
		this.bankCode = bankCode;
		this.reference = reference;
		this.identificationType = identificationType;
		this.identification = identification;
		this.date = date;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public GiftCard getGiftCard() {
		return giftCard;
	}

	public void setGiftCard(GiftCard giftCard) {
		this.giftCard = giftCard;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
