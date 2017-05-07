package payment.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;
import payment.exceptions.GiftCardNotFoundException;
import payment.models.GiftCard;
import payment.pojos.GiftCardCreator;
import payment.services.GiftCardService;

@RestController
@RequestMapping("/giftcard")
public class GiftCardController extends EstampalaController{
	
	@Autowired
	private GiftCardService service;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<GiftCard>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page, @RequestParam(value="page_size", defaultValue="10", required = false) int pageSize) {
		return new ResponseEntity<Page<GiftCard>>(service.findAll(page, pageSize), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GiftCard> get(@PathVariable UUID id) throws GiftCardNotFoundException {
		if(!service.exists(id)) {
			throw new GiftCardNotFoundException();
		}
		return new ResponseEntity<GiftCard>(service.find(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GiftCard> create(@RequestBody(required=false) GiftCardCreator element) throws EstampalaException {
		return new ResponseEntity<GiftCard>(service.save(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GiftCard> update(@PathVariable UUID id, @RequestBody GiftCardCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new GiftCardNotFoundException();
		}
		element.setGiftCard(id);
		return new ResponseEntity<GiftCard>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GiftCard> updatePatch(@PathVariable UUID id, @RequestBody GiftCardCreator element) throws EstampalaException {
		if(!service.exists(id)) {
			throw new GiftCardNotFoundException();
		}
		element.setGiftCard(id);
		return new ResponseEntity<GiftCard>(service.update(element), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping	
	(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws GiftCardNotFoundException {
		if(!service.exists(id)) {
			throw new GiftCardNotFoundException();
		}
		service.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The GiftCard was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
}
