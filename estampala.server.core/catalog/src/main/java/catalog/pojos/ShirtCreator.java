package catalog.pojos;

import java.util.UUID;

public class ShirtCreator {

	private UUID shirt;
	private UUID shirtStyle;
	private UUID shirtSize;
	private UUID shirtColor;
	private UUID shirtMaterial;


	public UUID getShirt() {
		return shirt;
	}
	public void setShirt(UUID shirt) {
		this.shirt = shirt;
	}
	public UUID getShirtStyle() {
		return shirtStyle;
	}
	public void setShirtStyle(UUID shirtStyle) {
		this.shirtStyle = shirtStyle;
	}
	public UUID getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(UUID shirtSize) {
		this.shirtSize = shirtSize;
	}
	public UUID getShirtColor() {
		return shirtColor;
	}
	public void setShirtColor(UUID shirtColor) {
		this.shirtColor = shirtColor;
	}
	public UUID getShirtMaterial() {
		return shirtMaterial;
	}
	public void setShirtMaterial(UUID shirtMaterial) {
		this.shirtMaterial = shirtMaterial;
	}
}
