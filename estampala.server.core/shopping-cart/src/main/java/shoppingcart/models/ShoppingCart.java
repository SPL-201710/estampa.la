package shoppingcart.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	private double subtotal;
	
	private double shippingValue;
	
	private double total;
	
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

	public List<SCProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<SCProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
