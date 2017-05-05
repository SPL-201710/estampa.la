package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class S3ImageUploadException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public S3ImageUploadException(String exception) {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("image_s3_upload_error");
		error.setSucess(false);
		error.setMessage("Image s3 upload error: " + exception);
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}
