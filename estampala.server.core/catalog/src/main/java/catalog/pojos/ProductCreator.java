package catalog.pojos;

import java.util.List;
import java.util.UUID;

public class ProductCreator {
	
	private UUID product;
	private double totalPrice;
	private List<ProductDesign> designs;
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<ProductDesign> getDesigns() {
		return designs;
	}
	public void setDesigns(List<ProductDesign> designs) {
		this.designs = designs;
	}
	public UUID getProduct() {
		return product;
	}
	public void setProduct(UUID product) {
		this.product = product;
	}	
}
