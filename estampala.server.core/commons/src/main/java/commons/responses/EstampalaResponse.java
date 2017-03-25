package commons.responses;

import org.springframework.http.HttpStatus;

public interface EstampalaResponse {
	
	public String getMessage();	
	public boolean isSuccess();
	public HttpStatus getHttpStatus();
}
