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
	
	private int maxHightInCentimeters;
	private int maxWidthInCentimeters;
	private String name;

	ShirtPrintPosition(){

	}
	
	public ShirtPrintPosition(UUID id, int maxHightInCentimeters, int maxWidthInCentimeters, String name) {
		super();
		this.id = id;
		this.maxHightInCentimeters = maxHightInCentimeters;
		this.maxWidthInCentimeters = maxWidthInCentimeters;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getMaxHightInCentimeters() {
		return maxHightInCentimeters;
	}

	public void setMaxHightInCentimeters(int maxHightInCentimeters) {
		this.maxHightInCentimeters = maxHightInCentimeters;
	}

	public int getMaxWidthInCentimeters() {
		return maxWidthInCentimeters;
	}

	public void setMaxWidthInCentimeters(int maxWidthInCentimeters) {
		this.maxWidthInCentimeters = maxWidthInCentimeters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}