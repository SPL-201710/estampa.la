package catalog.pojos;

import java.util.UUID;

public class ProductPrintsInShirt {

	private UUID print;
	private UUID shirtPrintPosition;
	
	public UUID getPrint() {
		return print;
	}
	public void setPrint(UUID print) {
		this.print = print;
	}
	public UUID getShirtPrintPosition() {
		return shirtPrintPosition;
	}
	public void setShirtPrintPosition(UUID shirtPrintPosition) {
		this.shirtPrintPosition = shirtPrintPosition;
	}
}
