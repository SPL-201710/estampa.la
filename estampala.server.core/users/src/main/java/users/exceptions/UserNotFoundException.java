package users.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class UserNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(UUID idUser) {
		
		if (idUser == null){
			idUser = new UUID(0, 0);
		}
		
		createError(idUser.toString());		
	}	
	
	public UserNotFoundException(String username) {
		
		if (username == null){
			username = "unknow";
		}
		createError(username);
	}	

	private void createError(String param) {	
		ErrorResponse error = new ErrorResponse();
		error.setError("user_not_found");
		error.setSucess(false);
		error.setMessage(String.format("The user %1 doesn't exist", param));
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}
