package catalog.models.product;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.print.PrintText;
import catalog.models.shirt.ShirtPrintPosition;

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
	
	@OneToOne(cascade=CascadeType.ALL)
	private PrintText printText;
	
	@ManyToOne
	private ShirtPrintPosition shirtPrintPosition;
		
	TextInShirt(){

	}


	public TextInShirt(UUID id, PrintText printText, ShirtPrintPosition shirtPrintPosition) {
		super();
		this.id = id;
		this.printText = printText;
		this.shirtPrintPosition = shirtPrintPosition;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public PrintText getPrintText() {
		return printText;
	}


	public void setPrintText(PrintText printText) {
		this.printText = printText;
	}


	public ShirtPrintPosition getShirtPrintPosition() {
		return shirtPrintPosition;
	}


	public void setShirtPrintPosition(ShirtPrintPosition shirtPrintPosition) {
		this.shirtPrintPosition = shirtPrintPosition;
	}

/*
	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}*/	
}