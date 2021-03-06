package users.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class InvalidTokenException extends EstampalaException {
	
	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {

		ErrorResponse error = new ErrorResponse();
		error.setError("invalid_token");
		error.setSucess(false);
		error.setMessage("Invalid token");
		error.setHttpStatus(HttpStatus.UNAUTHORIZED);

		setError(error);
	}

}
