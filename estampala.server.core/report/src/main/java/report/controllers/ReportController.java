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
import report.pojo.SalesReportByArtist;
import report.services.ReportService;


@RestController
@RequestMapping("/reports")
public class ReportController extends EstampalaController {

	@Autowired
	private ReportService service;

	@CrossOrigin
	@RequestMapping(value = "/salesbyartist/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SalesReportByArtist>> salesReportByArtist(@PathVariable UUID id,
			@RequestParam(value="start_date", required = false) Date startDate,
			@RequestParam(value="end_date", required = false) Date endDate,
			@RequestParam(value="id_print", required = false) UUID idPrint) {		
		
		return new ResponseEntity<List<SalesReportByArtist>>(service.salesReportByArtist(id, startDate, endDate, idPrint), HttpStatus.OK);
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
