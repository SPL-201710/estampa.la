package catalog.models.product;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
	private int totalPrice;
	
	@OneToMany
	private Collection<PrintInShirt> printsInShirts;
	
	@OneToMany
	private Collection<TextInShirt> textsInShirts;

	Product(){

	}
		
	public Product(UUID id, int totalPrice, Collection<PrintInShirt> printsInShirts, Collection<TextInShirt> textsInShirts) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;		
		this.printsInShirts = printsInShirts;
		this.textsInShirts = textsInShirts;
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

	public Collection<PrintInShirt> getPrintsInShirts() {
		return printsInShirts;
	}

	public void setPrintsInShirts(Collection<PrintInShirt> printsInShirts) {
		this.printsInShirts = printsInShirts;
	}

	public Collection<TextInShirt> getTextsInShirts() {
		return textsInShirts;
	}

	public void setTextsInShirts(Collection<TextInShirt> textsInShirts) {
		this.textsInShirts = textsInShirts;
	}	
}