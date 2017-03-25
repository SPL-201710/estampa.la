package commons.responses;

import org.springframework.http.HttpStatus;

public class ErrorResponse implements EstampalaResponse{

	private String message;
	private String error;
	private boolean sucess;
	private HttpStatus httpStatus;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	public HttpStatus getHttpStatus() {
		if (httpStatus == null){
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}	
}
