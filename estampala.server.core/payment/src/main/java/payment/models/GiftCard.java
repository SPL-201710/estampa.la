package payment.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class GiftCard {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID buyer;
	
	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID receiver;
	
	private Double initialBalance;
	
	private Double balance;
	
	private Boolean active;

	public GiftCard() {
		
	}
	
	public GiftCard(UUID id, UUID buyer, UUID receiver, Double initialBalance) {
		super();
		this.id = id;
		this.buyer = buyer;
		this.receiver = receiver;
		this.initialBalance = initialBalance;
		this.balance = initialBalance;
		this.active = true;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getBuyer() {
		return buyer;
	}

	public void setBuyer(UUID buyer) {
		this.buyer = buyer;
	}

	public UUID getReceiver() {
		return receiver;
	}

	public void setReceiver(UUID receiver) {
		this.receiver = receiver;
	}

	public double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
