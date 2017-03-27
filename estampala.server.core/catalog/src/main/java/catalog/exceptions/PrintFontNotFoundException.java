package catalog.exceptions;

import org.springframework.http.HttpStatus;

import commons.exceptions.EstampalaException;
import commons.responses.ErrorResponse;

public class PrintFontNotFoundException extends EstampalaException{

	private static final long serialVersionUID = 1L;

	public PrintFontNotFoundException() {
		
		ErrorResponse error = new ErrorResponse();
		error.setError("font_not_found");
		error.setSucess(false);
		error.setMessage("The font not exists");
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		
		setError(error);		
	}	
}
