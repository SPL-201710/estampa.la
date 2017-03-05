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
public class PrintInShirt {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;

	private int hightInCentimeters;
	private UUID idShirtPrintPosition;
	private int widthInCentimeters;
	public Print print;

	PrintInShirt(){

	}
		
	public PrintInShirt(UUID id, int hightInCentimeters, UUID idShirtPrintPosition, int widthInCentimeters,
			Print print) {
		super();
		this.id = id;
		this.hightInCentimeters = hightInCentimeters;
		this.idShirtPrintPosition = idShirtPrintPosition;
		this.widthInCentimeters = widthInCentimeters;
		this.print = print;
	}

	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public int getHightInCentimeters() {
		return hightInCentimeters;
	}


	public void setHightInCentimeters(int hightInCentimeters) {
		this.hightInCentimeters = hightInCentimeters;
	}


	public UUID getIdShirtPrintPosition() {
		return idShirtPrintPosition;
	}


	public void setIdShirtPrintPosition(UUID idShirtPrintPosition) {
		this.idShirtPrintPosition = idShirtPrintPosition;
	}


	public int getWidthInCentimeters() {
		return widthInCentimeters;
	}


	public void setWidthInCentimeters(int widthInCentimeters) {
		this.widthInCentimeters = widthInCentimeters;
	}


	public Print getPrint() {
		return print;
	}


	public void setPrint(Print print) {
		this.print = print;
	}		
}