package payment.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class ServiceNotAvailableException extends EstampalaException {

	private static final long serialVersionUID = 1L;

	public ServiceNotAvailableException() {

		ErrorResponse error = new ErrorResponse();
		error.setError("service_not_available");
		error.setSucess(false);
		error.setMessage("Service is not available");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);

		setError(error);
	}
}
