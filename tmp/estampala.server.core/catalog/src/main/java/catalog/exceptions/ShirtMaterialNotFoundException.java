package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ShirtMaterialNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public ShirtMaterialNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("material_not_found");
		error.setSucess(false);
		error.setMessage("The material not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}
