package report.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import catalog.models.print.Print;
import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import report.pojo.SalesReportByArtist;
import report.services.ReportService;


@RestController
@RequestMapping("/reports")
public class ReportController extends EstampalaController {

	@Autowired
	private ReportService service;

	@RequestMapping(value = "/salesbyartist/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SalesReportByArtist>> salesReportByArtist(@PathVariable UUID id) {
				
		List<SalesReportByArtist> list = new ArrayList<>();
		
		for(int i = 0; i < 30; i++){
			SalesReportByArtist s = new SalesReportByArtist();
			s.setPrintId(UUID.randomUUID());
			s.setPrintImage("https://s3.amazonaws.com/estampala/prints/02e6e127-7066-4aae-86a9-050561f7fa56.jpg");
			s.setPrintName("myprint_" + i);
			s.setQuantitySold(i+1);
			s.setTotalSold((i+1) * 3);
			
			list.add(s);
		}		
		
		return new ResponseEntity<List<SalesReportByArtist>>(list, HttpStatus.OK);
		
		//return new ResponseEntity<Page<SalesReportByArtist>>(service.salesReportByArtist(page, pageSize), HttpStatus.OK);
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
