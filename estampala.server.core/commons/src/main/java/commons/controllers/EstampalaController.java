package commons.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;
import commons.responses.SuccessResponse;

public abstract class EstampalaController {
		
	public abstract ResponseEntity<SuccessResponse> exist(@PathVariable UUID id) throws EstampalaException;
	
	@ExceptionHandler({EstampalaException.class, Exception.class})
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception) {
	
		ErrorResponse error;
		
		if (exception instanceof EstampalaException){
			error = ((EstampalaException) exception).getError();
		}
		else{
			error = new ErrorResponse();
			error.setError("unknow_error");
			error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			error.setSucess(false);
			error.setMessage(exception.getMessage() + " " + exception.getStackTrace());
		}		
		
		return new ResponseEntity<ErrorResponse>(error, error.getHttpStatus());
	}	
}
