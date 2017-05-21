package report.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import catalog.models.print.Print;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import report.pojo.SalesReportByArtist;

@Service
public class ReportService {

	@Autowired
	DataSource dataSource;
	
	public Page<SalesReportByArtist> salesReportByArtist(UUID idArtist, int page, int pageSize) {
		
		Connection connection;
		try {
			
			/*String query = "select COF_NAME, SUP_ID, PRICE, " +
	                   "SALES, TOTAL " +
	                   "from " + dbName + ".COFFEES";*/
			
			connection = dataSource.getConnection();				
			
	
		}catch (SQLException e) {
		}
		return null;
	}
}
