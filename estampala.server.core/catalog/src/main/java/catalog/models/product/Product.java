package catalog.models.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.shirt.Shirt;

/**
 * @author akane
 *
 */

@Entity
public class Product {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;

	@Column(nullable = false)
	private double totalPrice;

	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID owner;

	@ManyToOne
	private Shirt shirt;

	@OneToMany(cascade=CascadeType.ALL)
	private List<PrintInShirt> printsInShirts = new ArrayList<>();

	@OneToMany(cascade=CascadeType.ALL)
	private List<TextInShirt> textsInShirts = new ArrayList<>();

	Product(){

	}

	public Product(UUID id, double totalPrice, Shirt shirt, UUID owner, List<PrintInShirt> printsInShirts,	List<TextInShirt> textsInShirts) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.shirt = shirt;
		this.printsInShirts = printsInShirts;
		this.textsInShirts = textsInShirts;
		this.owner = owner;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<PrintInShirt> getPrintsInShirts() {
		return printsInShirts;
	}

	public void setPrintsInShirts(List<PrintInShirt> printsInShirts) {
		this.printsInShirts = printsInShirts;
	}

	public List<TextInShirt> getTextsInShirts() {
		return textsInShirts;
	}

	public void setTextsInShirts(List<TextInShirt> textsInShirts) {
		this.textsInShirts = textsInShirts;
	}

	public Shirt getShirt() {
		return shirt;
	}

	public void setShirt(Shirt shirt) {
		this.shirt = shirt;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}
}
