package catalog.models.print;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.theme.Theme;

/**
 * @author akane
 *
 */

@Entity
public class Print {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;

	@Column(nullable = true)
	private String description;

	@Column(nullable = false)
	private byte[] image;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private int popularity;

	@Column(nullable = false)
	private long price;

	@Column(nullable = true)
	private int rating;

	@ManyToOne
	private Theme theme;

	Print(){

	}

	public Print(UUID id, String description, byte[] image, String name, long price, int rating, int popularity,
			Theme theme) {
		super();
		this.id = id;
		this.description = description;
		this.image = image;
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.popularity = popularity;
		this.theme = theme;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
