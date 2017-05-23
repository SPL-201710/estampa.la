package report.services;

import java.util.List;

import report.pojo.Filters;
import report.pojo.ReportResponse;

public interface ReportService {
	public List<ReportResponse> sales(Filters filter);
}
