package users.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class CredentialsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public CredentialsException(String username) {

		if (username == null){
			username = "unknown";
		}

		ErrorResponse error = new ErrorResponse();
		error.setError("credentials_dont_match");
		error.setSucess(false);
		error.setMessage("The credentials for the user " + username + " don't match");
		error.setHttpStatus(HttpStatus.UNAUTHORIZED);

		setError(error);
	}
}
