package exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class CredentialsException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public CredentialsException(String username) {
		
		if (username == null){
			username = "unknow";
		}
		
		ErrorResponse error = new ErrorResponse();
		error.setError("credentials_dont_match");
		error.setSucess(false);
		error.setMessage(String.format("The credentials for user {0} don't match", username));
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);
	}
}
