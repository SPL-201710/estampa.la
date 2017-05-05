package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class S3KeysNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public S3KeysNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("s3_keys_not_found");
		error.setSucess(false);
		error.setMessage("The S3 key not found in environment variables: ESTAMPALA_S3_ACCESSKEY and ESTAMPALA_S3_SECRETKEY");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}
