package users.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class InvalidTokenException extends EstampalaException {

	public InvalidTokenException() {

		ErrorResponse error = new ErrorResponse();
		error.setError("invalid_token");
		error.setSucess(false);
		error.setMessage("Invalid token");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);

		setError(error);
	}

}
