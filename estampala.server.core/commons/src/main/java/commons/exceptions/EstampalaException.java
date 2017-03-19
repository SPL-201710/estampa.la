package commons.exceptions;

import org.springframework.http.HttpStatus;

import commons.responses.ErrorResponse;

public abstract class EstampalaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	protected ErrorResponse error;	
	protected HttpStatus httpStatus;
	
	protected EstampalaException(){
		
	}
	
	protected void setError(ErrorResponse error){
		if (error != null){
			this.error = error;
			this.httpStatus = error.getHttpStatus();
		}		
	}
	
	public ErrorResponse getError(){
		if (error == null){
			error = new ErrorResponse();
		}
		
		return error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}		
}
