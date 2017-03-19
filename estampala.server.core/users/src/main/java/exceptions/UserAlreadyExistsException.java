package exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class UserAlreadyExistsException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException(String username) {
		
		if (username == null){
			username = "unknow";
		}
		
		ErrorResponse error = new ErrorResponse();
		error.setError("user_already_exists");
		error.setSucess(false);
		error.setMessage(String.format("The user {0} already exists", username));
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}		
}
