package shoppingcart.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class ShoppingTransaction {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@NotNull
	private UUID transactionUser;
	
	@NotNull
	private UUID shoppingCart;
	
	@NotNull
	private Date date;
	
	public ShoppingTransaction() {
		date = new Date();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getTransactionUser() {
		return transactionUser;
	}

	public void setTransactionUser(UUID transactionUser) {
		this.transactionUser = transactionUser;
	}

	public UUID getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(UUID shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
