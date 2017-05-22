package report.pojo;

import java.util.Date;

public class SalesReportByShirtFeatures implements ReportResponse{
	
	private String shirtStyleName;
	private String shirtStyleImage;
	private String shirtColorName;
	private String shirtMaterialName;
	private int quantity;
	private double totalSold;
	private Date date;
	
	public String getShirtStyleName() {
		return shirtStyleName;
	}
	public void setShirtStyleName(String shirtStyleName) {
		this.shirtStyleName = shirtStyleName;
	}
	public String getShirtStyleImage() {
		return shirtStyleImage;
	}
	public void setShirtStyleImage(String shirtStyleImage) {
		this.shirtStyleImage = shirtStyleImage;
	}
	public String getShirtColorName() {
		return shirtColorName;
	}
	public void setShirtColorName(String shirtColorName) {
		this.shirtColorName = shirtColorName;
	}
	public String getShirtMaterialName() {
		return shirtMaterialName;
	}
	public void setShirtMaterialName(String shirtMaterialName) {
		this.shirtMaterialName = shirtMaterialName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalSold() {
		return totalSold;
	}
	public void setTotalSold(double totalSold) {
		this.totalSold = totalSold;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
