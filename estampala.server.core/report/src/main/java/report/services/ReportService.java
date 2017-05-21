package report.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import report.pojo.SalesReportByArtist;

@Service
public class ReportService {

	@Autowired
	DataSource dataSource;
	
	public List<SalesReportByArtist> salesReportByArtist(UUID idArtist, Date startDate, Date endDate, UUID idPrint) {
		List<SalesReportByArtist> records = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			
			StringBuilder query = new StringBuilder(600);
			query.append("SELECT print.id AS printId, name AS printName, image AS printImage, price, COUNT(print.id) AS count, (print.price * print.count) AS totalSold FROM print ");
			query.append("INNER JOIN printinshirt ON print.id = printinshirt.print_id ");
			
			if (idPrint != null){
				query.append("AND print.id = '");
				query.append(idPrint);
				query.append("' ");
			}			
			
			query.append("INNER JOIN product_printinshirt ON printinshirt.id = product_printinshirt.printsinshirts_id ");
			query.append("INNER JOIN scproduct ON product_printinshirt.product_id = scproduct.product ");
			query.append("INNER JOIN shoppingcart_scproduct ON scproduct.ID = shoppingcart_scproduct.cartproducts_id ");			
			query.append("INNER JOIN payment ON shoppingcart_scproduct.shoppingcart_id = payment.shoppingcart AND ");
			query.append("payment.owner = '");
			query.append(idArtist);
			query.append("' AND payment.date BETWEEN ? and ? GROUP BY print.id");
			
			if (startDate == null){
				startDate = new Date(65750400000L);
			}
			
			if (endDate == null){
				endDate = new Date();
			}			
			
			connection = dataSource.getConnection();			
			stmt = connection.prepareStatement(query.toString());
			stmt.setDate(1, new java.sql.Date(startDate.getTime()));
			stmt.setDate(2, new java.sql.Date(endDate.getTime()));
			
			ResultSet rs = stmt.executeQuery();			
			
	        while (rs.next()) {
	        	SalesReportByArtist s = new SalesReportByArtist();
	        	s.setPrintId(UUID.fromString(rs.getString("printid")));
	        	s.setPrintImage(rs.getString("printimage"));
	        	s.setPrintName(rs.getString("printname"));
	        	s.setQuantitySold(rs.getInt("count"));
	        	s.setTotalSold(rs.getDouble("totalsold"));	
	        	
	        	records.add(s);
	        }	        
	
		}catch (SQLException e) {
		
		} finally {
	        try { if (stmt != null) { stmt.close(); }}catch (SQLException e) {}
	    }
		
		return records;
	}
	
	public Page<SalesReportByArtist> salesReportByPrint(UUID idArtist) {
		return null;
	}
}
