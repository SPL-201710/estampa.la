package report.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import report.pojo.Filters;
import report.pojo.ReportResponse;

@Service
public class EmptyReportService implements ReportService {

	@Override
	public List<ReportResponse> sales(Filters filter) {		
		return new ArrayList<>();
	}
}
