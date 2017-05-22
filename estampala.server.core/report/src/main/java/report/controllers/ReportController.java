package report.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;
import report.pojo.Filters;
import report.pojo.ReportResponse;
import report.services.PrintsByRatingService;
import report.services.SalesArtistReportService;
import report.services.SalesShirtReportService;


@RestController
@RequestMapping("/reports")
public class ReportController extends EstampalaController {

	@Autowired
	private SalesArtistReportService salesArtistService;
	
	@Autowired
	private SalesShirtReportService salesShirtService;
	
	@Autowired
	private PrintsByRatingService printsRatingService;

	@CrossOrigin
	@RequestMapping(value = "/salesbyartist/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ReportResponse>> salesByArtist(			
			@PathVariable UUID id,
			@RequestParam(value="start_date", required = false) Date startDate,
			@RequestParam(value="end_date", required = false) Date endDate,
			@RequestParam(value="id_print", required = false) UUID idPrint) {		
		
		Filters filter = new Filters();
		filter.setEndDate(endDate);
		filter.setIdArtist(id);
		filter.setIdPrint(idPrint);
		filter.setStartDate(startDate);
		
		return new ResponseEntity<List<ReportResponse>>(salesArtistService.sales(filter), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/salesbyshirt", method = RequestMethod.GET)
	public ResponseEntity<List<ReportResponse>> salesByShirt(			
			@RequestParam(value="start_date", required = false) Date startDate,
			@RequestParam(value="end_date", required = false) Date endDate,
			@RequestParam(value="id_material", required = false) UUID idMaterial,
			@RequestParam(value="id_color", required = false) UUID idColor,
			@RequestParam(value="id_size", required = false) UUID idSize,
			@RequestParam(value="id_style", required = false) UUID idStyle) {		
		
		Filters filter = new Filters();
		filter.setEndDate(endDate);
		filter.setStartDate(startDate);		
		filter.setIdShirtColor(idColor);
		filter.setIdShirtMaterial(idMaterial);
		filter.setIdShirtSize(idSize);
		filter.setIdShirtStyle(idStyle);
		
		return new ResponseEntity<List<ReportResponse>>(salesShirtService.sales(filter), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/printsbyrating", method = RequestMethod.GET)
	public ResponseEntity<List<ReportResponse>> printsByRating(			
			@RequestParam(value="rating_min", required = true) float ratingMin,
			@RequestParam(value="rating_max", required = true) float ratingMax,
			@RequestParam(value="active", required = false) Boolean active) {		
		
		Filters filter = new Filters();
		filter.setRatingMin(ratingMin);
		filter.setRatingMax(ratingMax);		
		
		if (active == null || active){
			filter.setPrintActive(true);
		}
						
		return new ResponseEntity<List<ReportResponse>>(printsRatingService.sales(filter), HttpStatus.OK);
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/exist/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> exist(@PathVariable UUID id) throws EstampalaException {
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("Look success attribute");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
