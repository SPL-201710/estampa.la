package catalog.models.print;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author akane
 * 
 */

@Entity
public class PrintText {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@ManyToOne
	private PrintFont font;
	
	@ManyToOne
	private TextStyle textStyle;
	
	@Column(nullable = false)
	private String message;
	
	@Column(nullable = false)
	private int size;
	
	@Column(nullable = false)
	private String hexadecimalColor;
		
	PrintText(){

	}

	public PrintText(UUID id, PrintFont font, String message, int size, TextStyle textStyle, String hexadecimalColor) {
		super();
		this.id = id;
		this.font = font;
		this.message = message;
		this.size = size;
		this.textStyle = textStyle;
		this.hexadecimalColor = hexadecimalColor;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public PrintFont getFont() {
		return font;
	}

	public void setFont(PrintFont font) {
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

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public String getHexadecimalColor() {
		return hexadecimalColor;
	}

	public void setHexadecimalColor(String hexadecimalColor) {
		this.hexadecimalColor = hexadecimalColor;
	}	
}