package catalog.pojos;

import java.util.UUID;

public class ProductTextsInShirt {
	
	private ProductPrintText printText;
	private UUID shirtPrintPosition;
	
	
	public ProductPrintText getPrintText() {
		return printText;
	}
	public void setPrintText(ProductPrintText printText) {
		this.printText = printText;
	}
	public UUID getShirtPrintPosition() {
		return shirtPrintPosition;
	}
	public void setShirtPrintPosition(UUID shirtPrintPosition) {
		this.shirtPrintPosition = shirtPrintPosition;
	}	
}
