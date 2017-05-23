package report.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import report.pojo.Filters;
import report.pojo.PrintsByRating;
import report.pojo.ReportResponse;

@Service
public class PrintsByRatingService extends ReportDefault{

	public PreparedStatement getQuery(Connection connection, Filters filter) throws SQLException {
		PreparedStatement stmt = null;	
		
		StringBuilder query = new StringBuilder(600);
		query.append("SELECT print.name AS printname, theme.name AS themename, print.image AS image, estampalauser.username AS ownerusername, estampalauser.email AS email, print.price AS price, print.rating AS rating, print.ratingcounts AS ratingCounts FROM print ");
		query.append("INNER JOIN theme ON print.theme_id = theme.id AND print.active = ? AND print.rating BETWEEN ? AND ? ");
		query.append("INNER JOIN estampalauser ON print.owner = estampalauser.id");
							
		stmt = connection.prepareStatement(query.toString());
		stmt.setBoolean(1, filter.isPrintActive());
		stmt.setFloat(2, filter.getRatingMin());
		stmt.setFloat(3, filter.getRatingMax());			
			
		return stmt;
	}

	public List<ReportResponse> getResponse(ResultSet rs) throws SQLException{
		List<ReportResponse> records = new ArrayList<>();
				
		while (rs.next()) {
			PrintsByRating s = new PrintsByRating();
			
        	s.setImage(rs.getString("image"));
        	s.setName(rs.getString("printname"));
        	s.setOwnerUsername(rs.getString("ownerusername"));
        	s.setPrice(rs.getDouble("price"));
        	s.setRating(rs.getFloat("rating"));
        	s.setRatingCounts(rs.getInt("ratingCounts"));
        	s.setTheme(rs.getString("themename"));
        	s.setOwnerEmail(rs.getString("email"));
        	
        	records.add(s);
        }	
			
		return records;
	}
}
