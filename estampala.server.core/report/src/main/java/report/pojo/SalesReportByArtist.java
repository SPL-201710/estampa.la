package report.pojo;

import java.util.UUID;

public class SalesReportByArtist implements ReportResponse{
		
	private String printName;
	private UUID printId;
	private String printImage;
	private int quantitySold;
	private double totalSold;
	
	
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public UUID getPrintId() {
		return printId;
	}
	public void setPrintId(UUID printId) {
		this.printId = printId;
	}
	public String getPrintImage() {
		return printImage;
	}
	public void setPrintImage(String printImage) {
		this.printImage = printImage;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getTotalSold() {
		return totalSold;
	}
	public void setTotalSold(double totalSold) {
		this.totalSold = totalSold;
	}	
}
