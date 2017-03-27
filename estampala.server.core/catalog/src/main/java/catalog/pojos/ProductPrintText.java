package catalog.pojos;

import java.util.UUID;

public class ProductPrintText {

	private UUID font;
	private String message;
	private int size;
	
	public UUID getFont() {
		return font;
	}
	public void setFont(UUID font) {
		this.font = font;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}	
}
