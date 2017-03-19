package catalog.models.product;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.print.Print;
import catalog.models.shirt.ShirtPrintPosition;

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
		
	@Column(nullable = false)
	@ManyToOne	
	private Print print;

	@Column(nullable = false)
	@ManyToOne
	private ShirtPrintPosition shirtPrintPosition;
	
	PrintInShirt(){

	}

	public PrintInShirt(UUID id, Print print, ShirtPrintPosition shirtPrintPosition) {
		super();
		this.id = id;
		this.print = print;
		this.shirtPrintPosition = shirtPrintPosition;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Print getPrint() {
		return print;
	}

	public void setPrint(Print print) {
		this.print = print;
	}

	public ShirtPrintPosition getShirtPrintPosition() {
		return shirtPrintPosition;
	}

	public void setShirtPrintPosition(ShirtPrintPosition shirtPrintPosition) {
		this.shirtPrintPosition = shirtPrintPosition;
	}	
}