package commons.exceptions;

import org.springframework.http.HttpStatus;

import commons.responses.ErrorResponse;

public class ResourceNotFoundException extends EstampalaException {
		
		private static final long serialVersionUID = 1L;
			
			public ResourceNotFoundException(String serviceName) {
								
				createError(serviceName);		
			}	
			
			private void createError(String param) {	
				ErrorResponse error = new ErrorResponse();
				error.setError("resource_not_found");
				error.setSucess(false);
				error.setMessage("The requested resource is not available " + param);
				error.setHttpStatus(HttpStatus.NOT_FOUND);
				
				setError(error);		
			}	
}
