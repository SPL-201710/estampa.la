package commons.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public abstract class EstampalaController {
	
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
			error.setMessage(exception.getMessage());
		}		
		
		return new ResponseEntity<ErrorResponse>(error, error.getHttpStatus());
	}	
}