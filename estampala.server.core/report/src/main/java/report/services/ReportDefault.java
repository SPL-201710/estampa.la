package report.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import report.pojo.Filters;
import report.pojo.ReportResponse;

public abstract class ReportDefault implements ReportService{

	@Autowired
	DataSource dataSource;
	
	public final List<ReportResponse> sales(Filters filter) {
		List<ReportResponse> records = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		try {			
			connection = dataSource.getConnection();			
			stmt = getQuery(connection, filter);			
			ResultSet rs = stmt.executeQuery();			
			records = getResponse(rs);
	
		}catch (SQLException e) {
		
		} finally {
	        try { if (stmt != null) { stmt.close(); }}catch (SQLException e) {}
	    }
		
		return records;
	}

	public abstract PreparedStatement getQuery(Connection connection, Filters filter) throws SQLException;	
	public abstract List<ReportResponse> getResponse(ResultSet rs) throws SQLException;
}
