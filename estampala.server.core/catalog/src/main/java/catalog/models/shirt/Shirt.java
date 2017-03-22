package catalog.models.shirt;

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
public class Shirt {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@ManyToOne
	private ShirtStyle style;
	
	@ManyToOne
	private ShirtSize size;
	
	@ManyToOne
	private ShirtColor color;
			
	@ManyToOne
	private ShirtMaterial meterial;

	Shirt(){

	}

	public Shirt(UUID id, ShirtStyle style, ShirtSize size, ShirtColor color, ShirtMaterial meterial) {
		super();
		this.id = id;
		this.style = style;
		this.size = size;
		this.color = color;		
		this.meterial = meterial;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ShirtStyle getStyle() {
		return style;
	}

	public void setStyle(ShirtStyle style) {
		this.style = style;
	}

	public ShirtSize getSize() {
		return size;
	}

	public void setSize(ShirtSize size) {
		this.size = size;
	}

	public ShirtColor getColor() {
		return color;
	}

	public void setColor(ShirtColor color) {
		this.color = color;
	}

	public ShirtMaterial getMeterial() {
		return meterial;
	}

	public void setMeterial(ShirtMaterial meterial) {
		this.meterial = meterial;
	}		
}