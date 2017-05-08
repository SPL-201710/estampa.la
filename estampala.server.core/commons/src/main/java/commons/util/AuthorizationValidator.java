package commons.util;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import commons.responses.SuccessResponse;

public class AuthorizationValidator {

	public static boolean isValid(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if(!FeaturesFlag.SECURITY.isActive()){
			return true;
		}
		
		if("get".equalsIgnoreCase(request.getMethod())){
			return true;
		}
		
		if (!FeaturesFlag.CALIFICAR.isActive()){
			if (request.getServletPath().contains("/rate")){
				SuccessResponse msg = new SuccessResponse();
	            msg.setHttpStatus(HttpStatus.NOT_FOUND);
	            msg.setSuccess(false);
	            msg.setMessage("Resource Not Found");
	    		
	            Gson gson = new Gson();
	            
	            response.setStatus(HttpStatus.NOT_FOUND.value());
	            response.setContentType("application/json");
	            Writer writer = response.getWriter();
	            writer.write(gson.toJson(msg));
	            
	            return false;
			}
		}
		
        String authorizationHeader = request.getHeader("Authorization");
        SuccessResponse permission = EstampalaTools.isTokenValid(authorizationHeader);
        if(permission != null && permission.isSuccess()) {        	
        	request.setAttribute("idUser", permission.getMessage());
            return permission.isSuccess();
        }
        else {            
            SuccessResponse msg = new SuccessResponse();
            msg.setHttpStatus(HttpStatus.UNAUTHORIZED);
            msg.setSuccess(false);
            msg.setMessage("Access denied");
    		
            Gson gson = new Gson();
            
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            Writer writer = response.getWriter();
            writer.write(gson.toJson(msg));
            
            return false;
        }        
    }	
}
