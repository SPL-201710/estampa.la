package payment.models;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author akane
 *
 */

@Entity
public class Payment {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private double total;

	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID owner;

	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID shoppingcart;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private PaymentMethod paymentMethod;

	Payment(){

	}

	public Payment(UUID id, Date date, double total, UUID owner, UUID shoppingcart) {
		super();
		this.id = id;
		this.date = date;
		this.total = total;
		this.owner = owner;
		this.shoppingcart = shoppingcart;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public UUID getShoppingcart() {
		return shoppingcart;
	}
	public void setShoppingcart(UUID shoppingcart) {
		this.shoppingcart = shoppingcart;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
