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
public class ShirtColor {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = true)
	@Type(type="pg-uuid")
	private UUID id;

	@Column(nullable = false)
	private byte[] image;

	@Column(nullable = false)
	private String hexadecimalValue;

	@Column(nullable = false)
	private String name;


	ShirtColor(){

	}

	public ShirtColor(UUID id, byte[] image, String hexadecimalValue, String name) {
		super();
		this.id = id;
		this.image = image;
		this.hexadecimalValue = hexadecimalValue;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getHexadecimalValue() {
		return hexadecimalValue;
	}

	public void setHexadecimalValue(String hexadecimalValue) {
		this.hexadecimalValue = hexadecimalValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
