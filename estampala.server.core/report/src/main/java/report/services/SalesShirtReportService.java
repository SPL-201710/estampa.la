package report.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import report.pojo.Filters;
import report.pojo.ReportResponse;
import report.pojo.SalesReportByShirtFeatures;

@Service
public class SalesShirtReportService extends Report{

	@Override
	public PreparedStatement getQuery(Connection connection, Filters filter) throws SQLException {
		PreparedStatement stmt = null;	
		
		StringBuilder query = new StringBuilder(1000);
		query.append("SELECT payment.date AS paymentdate, shirtcolor.name AS colorname, shirtstyle.name AS stylename, shirtstyle.image AS styleimage, shirtmaterial.name AS materialname, scproduct.quantity AS quantity, (scproduct.quantity * scproduct.subtotal) AS total FROM payment ");
		query.append("INNER JOIN shoppingcart_scproduct ON shoppingcart_scproduct.shoppingcart_id = payment.shoppingcart ");
		query.append("AND payment.date BETWEEN ? and ? ");
		query.append("INNER JOIN scproduct ON scproduct.id = shoppingcart_scproduct.cartproducts_id ");
		query.append("INNER JOIN product ON product.id = scproduct.product ");
		query.append("INNER JOIN shirt ON shirt.id = product.shirt_id ");
		query.append("INNER JOIN shirtcolor ON shirt.color_id = shirtcolor.id ");
		
		if (filter.getIdShirtColor() != null){
			query.append("AND shirtcolor.id = '");
			query.append(filter.getIdShirtColor());
			query.append("' ");
		}
		
		query.append("INNER JOIN shirtmaterial ON shirt.material_id = shirtmaterial.id ");
		
		if (filter.getIdShirtMaterial() != null){
			query.append("AND shirtmaterial.id = '");
			query.append(filter.getIdShirtMaterial());
			query.append("' ");
		}
		
		query.append("INNER JOIN shirtsize ON shirt.size_id = shirtsize.id ");
		
		if (filter.getIdShirtSize() != null){
			query.append("AND shirtsize.id = '");
			query.append(filter.getIdShirtSize());
			query.append("' ");
		}
		
		query.append("INNER JOIN shirtstyle ON shirt.style_id = shirtstyle.id ");
		
		if (filter.getIdShirtStyle() != null){
			query.append("AND shirtstyle.id = '");
			query.append(filter.getIdShirtStyle());
			query.append("' ");
		}
				
		
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

	@Override
	public List<ReportResponse> getResponse(ResultSet rs) throws SQLException {
		List<ReportResponse> records = new ArrayList<>();
		
		while (rs.next()) {
        	SalesReportByShirtFeatures s = new SalesReportByShirtFeatures();
        	s.setDate(rs.getDate("paymentdate"));
        	s.setQuantity(rs.getInt("quantity"));
        	s.setShirtColorName(rs.getString("colorname"));
        	s.setShirtMaterialName(rs.getString("materialname"));
        	s.setShirtStyleImage(rs.getString("styleimage"));
        	s.setShirtStyleName(rs.getString("stylename"));
        	s.setTotalSold(rs.getDouble("total"));
        	
        	records.add(s);
        }	
			
		return records;
	}	
}
