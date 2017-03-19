package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtMaterialAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtMaterialAlreadyExistsException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("material_already_exists");
		error.setSucess(false);
		error.setMessage("The material already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}
