package users.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class UserNotActiveException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public UserNotActiveException(UUID idUser) {
		
		if (idUser == null){
			idUser = new UUID(0, 0);
		}
		
		createError(idUser.toString());		
	}	
	
	public UserNotActiveException(String username) {
		
		if (username == null){
			username = "unknow";
		}
		createError(username);
	}	

	private void createError(String param) {	
		ErrorResponse error = new ErrorResponse();
		error.setError("user_not_active");
		error.setSucess(false);
		error.setMessage(String.format("The user %1 is not active", param));
		error.setHttpStatus(HttpStatus.UNAUTHORIZED);
		
		setError(error);		
	}	
}
