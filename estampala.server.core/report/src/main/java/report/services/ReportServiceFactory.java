package report.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.util.FeaturesFlag;

@Service
public class ReportServiceFactory {

	@Autowired
	private SalesArtistReportService salesArtistService;
	
	@Autowired
	private SalesShirtReportService salesShirtService;
	
	@Autowired
	private PrintsByRatingService printsRatingService;
	
	@Autowired
	private EmptyReportService emptyReportService;
	
	public ReportService getInstance(ReportServiceTypes type){
		if (type == ReportServiceTypes.PRINTS_BY_RATING &&
			FeaturesFlag.ESTAMPAS_RATING.isActive()){
			
			return printsRatingService;
		}
		else if (type == ReportServiceTypes.SALES_BY_ARTIST  &&
			FeaturesFlag.VENTAS_USUARIO.isActive()){
			
			return salesArtistService;
		}
		else if (type == ReportServiceTypes.SALES_BY_SHIRTS  &&
			FeaturesFlag.ESTADO_VENTAS.isActive()){
			
			return salesShirtService;
		}
		
		return emptyReportService;
	}	
}
