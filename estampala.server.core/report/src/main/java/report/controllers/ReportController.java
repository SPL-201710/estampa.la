package report.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import catalog.models.print.Print;
import commons.controllers.EstampalaController;
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

	@RequestMapping(value = "/salesByArtist/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page<SalesReportByArtist>> salesReportByArtist(@PathVariable UUID id, @RequestParam(value="page", defaultValue="1", required = false) int page, 
											@RequestParam(value="page_size", defaultValue="10", required = false) int pageSize,											
											@And({	@Spec(path = "date", spec = Like.class), 
													@Spec(path = "theme", spec = Like.class),
													@Spec(path = "print", spec = Like.class)}) Specification<Print> spec) {
		
				
		return new ResponseEntity<Page<SalesReportByArtist>>(service.salesReportByArtist(page, pageSize, spec), HttpStatus.OK);
	}	
}
