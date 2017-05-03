package catalog.pojos;

import java.util.UUID;

public class ProductPrintText {

	private UUID font;
	private String message;
	private int size;
	private UUID textStyle;
	private String hexadecimalColor;
	
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
	public UUID getTextStyle() {
		return textStyle;
	}
	public void setTextStyle(UUID textStyle) {
		this.textStyle = textStyle;
	}
	public String getHexadecimalColor() {
		return hexadecimalColor;
	}
	public void setHexadecimalColor(String hexadecimalColor) {
		this.hexadecimalColor = hexadecimalColor;
	}	
}
