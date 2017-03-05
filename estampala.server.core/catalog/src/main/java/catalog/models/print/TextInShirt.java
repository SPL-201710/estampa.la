package catalog.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

/**
 * @author akane
 * 
 */

@Entity
public class TextInShirt {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	private Fonts font;
	private UUID idShirtPrintPosition;
	private String message;
	private int size;

	TextInShirt(){

	}
	
	public TextInShirt(UUID id, Fonts font, UUID idShirtPrintPosition, String message, int size) {
		super();
		this.id = id;
		this.font = font;
		this.idShirtPrintPosition = idShirtPrintPosition;
		this.message = message;
		this.size = size;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Fonts getFont() {
		return font;
	}

	public void setFont(Fonts font) {
		this.font = font;
	}

	public UUID getIdShirtPrintPosition() {
		return idShirtPrintPosition;
	}

	public void setIdShirtPrintPosition(UUID idShirtPrintPosition) {
		this.idShirtPrintPosition = idShirtPrintPosition;
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