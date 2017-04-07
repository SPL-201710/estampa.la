package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class RenderingImageException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public RenderingImageException() {

		ErrorResponse error = new ErrorResponse();
		error.setError("rendering_image_failed");
		error.setSucess(false);
		error.setMessage("rendering image failed");
		error.setHttpStatus(HttpStatus.BAD_REQUEST);

		setError(error);
	}
}
