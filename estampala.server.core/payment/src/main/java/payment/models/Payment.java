package payment.models;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private double value;

	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID user_id;

	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID product;

	Payment(){

	}

	public Payment(UUID id, Date date, double value, UUID user_id, UUID product) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.user_id = user_id;
		this.product = product;
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
	public UUID getUser_id() {
		return user_id;
	}
	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public UUID getProduct() {
		return product;
	}
	public void setProduct(UUID product) {
		this.product = product;
	}
}
