package catalog.pojos;

import java.util.List;
import java.util.UUID;

public class ProductCreator {
	
	private UUID product;
	private double totalPrice;	
	private UUID shirt;
	private List<ProductPrintsInShirt> printsInShirts;
	private List<ProductTextsInShirt> textsInShirts;
	
	
	public UUID getProduct() {
		return product;
	}
	public void setProduct(UUID product) {
		this.product = product;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public UUID getShirt() {
		return shirt;
	}
	public void setShirt(UUID shirt) {
		this.shirt = shirt;
	}
	public List<ProductPrintsInShirt> getPrintsInShirts() {
		return printsInShirts;
	}
	public void setPrintsInShirts(List<ProductPrintsInShirt> printsInShirts) {
		this.printsInShirts = printsInShirts;
	}
	public List<ProductTextsInShirt> getTextsInShirts() {
		return textsInShirts;
	}
	public void setTextsInShirts(List<ProductTextsInShirt> textsInShirts) {
		this.textsInShirts = textsInShirts;
	}		
}
