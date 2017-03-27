package authorization;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.responses.EstampalaResponse;
import commons.util.Util;

public class AuthorizationFilter implements Filter {

	@Override
	public void  init(FilterConfig config) throws ServletException{
	 
	}
	
	@Override
	public void  doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws java.io.IOException, ServletException {
	
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		EstampalaResponse validToken = Util.validateToken(request);
		
		if(validToken.isSuccess()){
			chain.doFilter(request,res);
		}
		else{			
			Util.sendHttpUnauthorizedResponse(response, validToken.getMessage());
		}		
	}
	
	@Override
	public void destroy( ){
	
	}		
}
