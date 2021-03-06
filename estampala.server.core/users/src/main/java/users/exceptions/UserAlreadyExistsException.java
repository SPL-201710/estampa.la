package users.exceptions;

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
		error.setMessage("The user " + username + " already exists");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}		
}
