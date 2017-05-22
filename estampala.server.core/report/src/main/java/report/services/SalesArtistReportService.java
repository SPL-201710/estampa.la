package report.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import report.pojo.Filters;
import report.pojo.ReportResponse;
import report.pojo.SalesReportByArtist;

@Service
public class SalesArtistReportService extends Report{
		
	public PreparedStatement getQuery(Connection connection, Filters filter) throws SQLException {
		PreparedStatement stmt = null;	
		
		StringBuilder query = new StringBuilder(600);
		query.append("SELECT print.id AS printId, name AS printName, image AS printImage, price, COUNT(print.id) AS count, (print.price * print.count) AS totalSold FROM print ");
		query.append("INNER JOIN printinshirt ON print.id = printinshirt.print_id ");
		
		if (filter.getIdPrint() != null){
			query.append("AND print.id = '");
			query.append(filter.getIdPrint());
			query.append("' ");
		}			
		
		query.append("INNER JOIN product_printinshirt ON printinshirt.id = product_printinshirt.printsinshirts_id ");
		query.append("INNER JOIN scproduct ON product_printinshirt.product_id = scproduct.product ");
		query.append("INNER JOIN shoppingcart_scproduct ON scproduct.ID = shoppingcart_scproduct.cartproducts_id ");			
		query.append("INNER JOIN payment ON shoppingcart_scproduct.shoppingcart_id = payment.shoppingcart AND ");
		query.append("payment.owner = '");
		query.append(filter.getIdArtist());
		query.append("' AND payment.date BETWEEN ? and ? GROUP BY print.id");
		
		if (filter.getStartDate() == null){
			filter.setStartDate(new Date(65750400000L));
		}
		
		if (filter.getEndDate() == null){
			filter.setEndDate(new Date());
		}
					
		stmt = connection.prepareStatement(query.toString());
		stmt.setDate(1, new java.sql.Date(filter.getStartDate().getTime()));
		stmt.setDate(2, new java.sql.Date(filter.getEndDate().getTime()));			
			
		return stmt;
	}

	public List<ReportResponse> getResponse(ResultSet rs) throws SQLException{
		List<ReportResponse> records = new ArrayList<>();
				
		while (rs.next()) {
        	SalesReportByArtist s = new SalesReportByArtist();
        	s.setPrintId(UUID.fromString(rs.getString("printid")));
        	s.setPrintImage(rs.getString("printimage"));
        	s.setPrintName(rs.getString("printname"));
        	s.setQuantitySold(rs.getInt("count"));
        	s.setTotalSold(rs.getDouble("totalsold"));	
        	
        	records.add(s);
        }	
			
		return records;
	}
}
