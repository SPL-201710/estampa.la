package catalog.models.shirt;

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
public class ShirtPrintPosition {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@Column(nullable = false)
	private int hightInCentimeters;
	
	@Column(nullable = false)
	private int widthInCentimeters;
	
	@Column(nullable = false)
	private String name;

	ShirtPrintPosition(){

	}
	
	public ShirtPrintPosition(UUID id, int hightInCentimeters, int widthInCentimeters, String name) {
		super();
		this.id = id;
		this.hightInCentimeters = hightInCentimeters;
		this.widthInCentimeters = widthInCentimeters;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getMaxHightInCentimeters() {
		return hightInCentimeters;
	}

	public void setHightInCentimeters(int hightInCentimeters) {
		this.hightInCentimeters = hightInCentimeters;
	}

	public int getWidthInCentimeters() {
		return widthInCentimeters;
	}

	public void setWidthInCentimeters(int widthInCentimeters) {
		this.widthInCentimeters = widthInCentimeters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}