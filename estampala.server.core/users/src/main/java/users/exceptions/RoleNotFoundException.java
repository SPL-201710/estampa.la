package users.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class RoleNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public RoleNotFoundException() {		
		ErrorResponse error = new ErrorResponse();
		error.setError("role_not_found");
		error.setSucess(false);
		error.setMessage("The role not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);
	}
}
