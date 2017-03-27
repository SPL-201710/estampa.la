package payment.models;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.product.Product;
import users.models.User;

/**
 * @author akane
 * 
 */

@Entity
public class Payment {	
	
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@Column(nullable = false)	
	private Date date;

	@Column(nullable = false)
	private double value;
	
	@ManyToOne
	private User user;	
	
	@OneToOne
	private Product product;
	
	Payment(){
		
	}
			
	public Payment(UUID id, Date date, double value, User user, Product product) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.user = user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}	
}
