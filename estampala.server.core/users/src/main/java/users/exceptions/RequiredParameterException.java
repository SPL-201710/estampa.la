package users.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class RequiredParameterException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public RequiredParameterException(String parameter) {		
		ErrorResponse error = new ErrorResponse();
		error.setError("required_parameter_not_found");
		error.setSucess(false);
		error.setMessage(String.format("The parameter {0} is required", parameter));
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);
	}
}