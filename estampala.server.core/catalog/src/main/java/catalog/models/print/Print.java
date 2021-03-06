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
	private String image;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private long popularity;

	@Column(nullable = false)
	private long price;

	@Column(nullable = true)
	private float rating;

	@Column(nullable = true)
	private long ratingCounts;
	
	@ManyToOne
	private Theme theme;

	@Column(nullable = false)
	@Type(type="pg-uuid")
	private UUID owner;
	
	@Column(nullable = true)	
	private String ownerUsername;
	
	@Column(nullable = true)
	private Boolean active;
	
	public Print() {

	}

	public Print(UUID id, String description, String image, String name, long price, int rating, long popularity, Theme theme, UUID owner, String ownerUsername, long ratingCounts){
		super();
		this.id = id;
		this.description = description;
		this.image = image;
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.popularity = popularity;
		this.theme = theme;
		this.owner = owner;
		this.ownerUsername = ownerUsername;
		this.active= true;
		this.ratingCounts = ratingCounts;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPopularity() {
		return popularity;
	}

	public void setPopularity(long popularity) {
		this.popularity = popularity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public long getRatingCounts() {
		return ratingCounts;
	}

	public void setRatingCounts(long ratingCounts) {
		this.ratingCounts = ratingCounts;
	}	
}
