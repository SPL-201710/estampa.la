package report.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
