package authorization;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import commons.responses.SuccessResponse;
import commons.util.Endpoints;
import commons.util.EstampalaTools;

public class AuthorizationFilter implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");
        boolean permission = getPermission(authorizationHeader);
        if(permission) {
            return permission;
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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    
    private boolean getPermission(String authorizationHeader){
    	return true;
    	/*
    	if (authorizationHeader != null && authorizationHeader.isEmpty()){
    		String[] item = authorizationHeader.trim().split(" ");
    		
    		if (item.length == 2 && item[0].equalsIgnoreCase("token")){    			
    			Map<String, String> parameters = new HashMap<>();
    			parameters.put("token", item[1]);
    			
    			SuccessResponse res = EstampalaTools.invokeGetRestServices(Endpoints.IS_TOKEN_VALID.getPath(), null, parameters, SuccessResponse.class);
    			if (res != null)
    			return res.isSuccess();
    		}
    	}
    	
    	return false;*/
    }
}
