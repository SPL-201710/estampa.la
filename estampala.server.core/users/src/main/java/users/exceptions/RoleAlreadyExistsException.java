package users.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class RoleAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public RoleAlreadyExistsException() {		
		
		ErrorResponse error = new ErrorResponse();
		error.setError("role_already_exists");
		error.setSucess(false);
		error.setMessage("The role already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}		
}
