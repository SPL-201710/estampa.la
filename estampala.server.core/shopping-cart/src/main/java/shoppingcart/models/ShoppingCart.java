package shoppingcart.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class ShoppingCart {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;

	@NotNull
	private double subtotal;

	@NotNull
	private double shippingValue;

	@NotNull
	private double total;

	@NotNull
	@Type(type="pg-uuid")
	private UUID owner;

	@NotNull
	private Date date;

	@OneToMany(cascade = CascadeType.ALL)
	private List<SCProduct> cartProducts;

	public ShoppingCart() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getShippingValue() {
		return shippingValue;
	}

	public void setShippingValue(double shippingValue) {
		this.shippingValue = shippingValue;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<SCProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<SCProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
