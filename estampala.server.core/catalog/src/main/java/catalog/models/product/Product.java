package catalog.models.product;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.print.PrintInShirt;
import catalog.models.print.TextInShirt;
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
	
	private int totalPrice;
	public Shirt shirt;
	public PrintInShirt printsInShirt;
	public TextInShirt textsInShirt;

	Product(){

	}
		
	public Product(UUID id, int totalPrice, Shirt shirt, PrintInShirt printsInShirt, TextInShirt textsInShirt) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.shirt = shirt;
		this.printsInShirt = printsInShirt;
		this.textsInShirt = textsInShirt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Shirt getShirt() {
		return shirt;
	}

	public void setShirt(Shirt shirt) {
		this.shirt = shirt;
	}

	public PrintInShirt getPrintsInShirt() {
		return printsInShirt;
	}

	public void setPrintsInShirt(PrintInShirt printsInShirt) {
		this.printsInShirt = printsInShirt;
	}

	public TextInShirt getTextsInShirt() {
		return textsInShirt;
	}

	public void setTextsInShirt(TextInShirt textsInShirt) {
		this.textsInShirt = textsInShirt;
	}	
}