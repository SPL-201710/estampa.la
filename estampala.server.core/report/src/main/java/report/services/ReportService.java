package report.services;

import java.sql.Connection;
import java.sql.SQLException;

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
	
	public Page<SalesReportByArtist> salesReportByArtist(int page, int pageSize, Specification<Print> spec) {
		
		Connection connection;
		try {
			connection = dataSource.getConnection();				
			JasperReportBuilder report = DynamicReports.report();
			
			report
			  .columns(
			      Columns.column("Customer Id", "id", DataTypes.integerType()),
			      Columns.column("First Name", "first_name", DataTypes.stringType()),
			      Columns.column("Last Name", "last_name", DataTypes.stringType()),
			      Columns.column("Date", "date", DataTypes.dateType()))
			  .title(//title of the report
			      Components.text("SimpleReportExample")
				  .setHorizontalAlignment(HorizontalAlignment.CENTER))
				  .pageFooter(Components.pageXofY())//show page number on the page footer
				  .setDataSource("SELECT id, first_name, last_name, date FROM customers", connection);
	
		}catch (SQLException e) {
		}
		return null;
	}
}
