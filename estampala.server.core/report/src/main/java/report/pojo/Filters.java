package report.pojo;

import java.util.Date;
import java.util.UUID;

public class Filters {
	private UUID idArtist;
	private Date startDate;
	private Date endDate;
	private UUID idPrint;
	private UUID idShirtStyle;
	private UUID idShirtMaterial;
	private UUID idShirtColor;
	private UUID idShirtSize;
	private float ratingMin;
	private float ratingMax;
	private boolean printActive;
	
	public UUID getIdArtist() {
		return idArtist;
	}
	public void setIdArtist(UUID idArtist) {
		this.idArtist = idArtist;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public UUID getIdPrint() {
		return idPrint;
	}
	public void setIdPrint(UUID idPrint) {
		this.idPrint = idPrint;
	}
	public UUID getIdShirtStyle() {
		return idShirtStyle;
	}
	public void setIdShirtStyle(UUID idShirtStyle) {
		this.idShirtStyle = idShirtStyle;
	}
	public UUID getIdShirtMaterial() {
		return idShirtMaterial;
	}
	public void setIdShirtMaterial(UUID idShirtMaterial) {
		this.idShirtMaterial = idShirtMaterial;
	}
	public UUID getIdShirtColor() {
		return idShirtColor;
	}
	public void setIdShirtColor(UUID idShirtColor) {
		this.idShirtColor = idShirtColor;
	}
	public UUID getIdShirtSize() {
		return idShirtSize;
	}
	public void setIdShirtSize(UUID idShirtSize) {
		this.idShirtSize = idShirtSize;
	}
	public float getRatingMin() {
		return ratingMin;
	}
	public void setRatingMin(float ratingMin) {
		this.ratingMin = ratingMin;
	}
	public float getRatingMax() {
		return ratingMax;
	}
	public void setRatingMax(float ratingMax) {
		this.ratingMax = ratingMax;
	}
	public boolean isPrintActive() {
		return printActive;
	}
	public void setPrintActive(boolean printActive) {
		this.printActive = printActive;
	}	
}
