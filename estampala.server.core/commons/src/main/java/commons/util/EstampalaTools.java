package commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import commons.responses.ErrorResponse;

public class EstampalaTools {
	
	public static boolean validateToken(String authorizationHeader){		
		if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
			StringTokenizer st = new StringTokenizer(authorizationHeader);
			if (st.hasMoreTokens()) {		    	  		        
				try {		        	  
					String type = st.nextToken();
					String token = st.nextToken();	
					
					return callValidationTokenService(type, token);    	  	            
				} catch (Exception e) {
					return false;
				}		       		        
			}
		}
		
		return false;
	}
	
	public static void sendHttpUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
	    ErrorResponse res = new ErrorResponse();
	    res.setError("user_unauthorized");
	    res.setMessage(message);
	    res.setHttpStatus(HttpStatus.UNAUTHORIZED);
	    res.setSucess(false);
		
	    Gson gson = new Gson();
	    String json = gson.toJson(res);
	    
		response.setHeader("WWW-Authenticate", "Token realm=\"estampala\"");
	    response.sendError(401, json);	    
	}
	
	public static <T> T invokeGetRestServices(Endpoints endpoint, List<String> pathParameters, Map<String, String> queryParams, Class<T> returnType){
		return invokeGetRestServices(endpoint.getPath(), pathParameters, queryParams, returnType);
	}
	
	public static <T> T invokeGetRestServices(String baseUrl, List<String> pathParameters, Map<String, String> queryParams, Class<T> returnType){		
		InputStream inputStream = null;
		try {						
			StringBuilder url = new StringBuilder(baseUrl);
			if (baseUrl.endsWith("/")){
				url.deleteCharAt(url.length() - 1);				
			}			
			
			if (pathParameters != null){
				for(String value : pathParameters){
					url.append("/");
					url.append(value);					
				}
			}
			
			if (queryParams != null){
				url.append("?");
				for(String key : queryParams.keySet()){
					url.append(key);
					url.append("=");
					url.append(queryParams.get(key));
					url.append("&");
				}
			}
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url.toString());
						
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                                       
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;                    
                }

            };
			
            String content = httpclient.execute(httpGet, responseHandler);
			
			Gson gson = new Gson();			
			
			return gson.fromJson(content, returnType);
		
		} catch (Exception e) {
			return null;
	    } finally {
	    	try{
		    	if (inputStream != null){
		    		inputStream.close();
		    	}
	    	}catch(Exception e){};
	    }
	}

	private static boolean callValidationTokenService(String type, String token){		
		InputStream inputStream = null;
		try {
			if (token == null || token.isEmpty()){
				return false;
			}
						
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(Endpoints.VALIDATE_TOKEN.getPath());
	
			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
			params.add(new BasicNameValuePair("token", token));			
			httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
				
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
	
			if (entity != null) {				
				inputStream = entity.getContent();
				String content = IOUtils.toString(inputStream,"utf-8"); 
				JSONObject jsonObj = new JSONObject(content);
				return jsonObj.getBoolean("success");
			}
		
		} catch (Exception e) {
			return false;
	    } finally {
	    	try{
		    	if (inputStream != null){
		    		inputStream.close();
		    	}
	    	}catch(Exception e){};
	    }
		
		return false;
	}		
}
