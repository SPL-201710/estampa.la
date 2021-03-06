package catalog.pojos;

import java.util.UUID;

public class PrintCreator {

	private UUID print;
	private UUID theme;
	private UUID owner;

	private String description;
	private byte[] image;
	private String imageExtension;
	private String name;
	private long 	price;
	private int rating;
	private int popularity;
	private String ownerUsername;

	public UUID getPrint() {
		return this.print;
	}

	public void setPrint(UUID print) {
		this.print = print;
	}

	public UUID getTheme() {
		return this.theme;
	}

	public void setTheme(UUID theme) {
		this.theme = theme;
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

	public String getImageExtension() {
		return imageExtension;
	}

	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}	
}
