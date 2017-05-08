package commons.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class OwnerNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;
	
	public OwnerNotFoundException(UUID idUser) {
		
		if (idUser == null){
			idUser = new UUID(0, 0);
		}
		
		createError(idUser.toString());		
	}	
	
	public OwnerNotFoundException(String username) {
		
		if (username == null){
			username = "unknow";
		}
		createError(username);
	}	

	private void createError(String param) {	
		ErrorResponse error = new ErrorResponse();
		error.setError("owner_not_found");
		error.setSucess(false);
		error.setMessage("The owner " + param + " doesn't exist");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		setError(error);		
	}	
}
