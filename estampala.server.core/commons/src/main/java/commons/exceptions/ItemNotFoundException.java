package commons.exceptions;

import org.springframework.http.HttpStatus;

import commons.responses.ErrorResponse;

public class ItemNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("item_not_found");
		error.setSucess(false);
		error.setMessage("The item doesn't exist");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}

}
