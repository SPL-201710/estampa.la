package commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import commons.responses.EstampalaResponse;
import commons.responses.SuccessResponse;

public class EstampalaTools {
	
	public static EstampalaResponse validateToken(HttpServletRequest request){
		EstampalaResponse response = new ErrorResponse();
		
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
		      StringTokenizer st = new StringTokenizer(authHeader);
		      if (st.hasMoreTokens()) {
		        String type = st.nextToken();

		        if (type.equalsIgnoreCase("Token")) {
		          try {
		            String token = st.nextToken();		            
		            if (EstampalaTools.callValidationTokenService(token))
		            {
		            	SuccessResponse success = new SuccessResponse();
		            	success.setSuccess(true);
		            	success.setMessage("token is valid");
		            	success.setHttpStatus(HttpStatus.OK);
		            	
		            	response = success;
		            }		          			            
		          } catch (Exception e) {
		        	  ErrorResponse error = new ErrorResponse();
		        	  error.setSucess(false);
		        	  error.setError("user_unauthorized");
		        	  error.setMessage(e.getMessage());
		        	  
		        	  response = error;
		          }
		        }
		        else{
		        	ErrorResponse error = new ErrorResponse();
		        	error.setSucess(false);
		        	error.setError("user_unauthorized");
		        	error.setMessage("The type of authentication sent is not supported, only Token is allowed");
  
		        	response = error;		        	
		        }		        
		      }
		      else {
		        	ErrorResponse error = new ErrorResponse();
		        	error.setSucess(false);
		        	error.setError("user_unauthorized");
		        	error.setMessage("An authentication type must be provided");
  
		        	response = error;		    	  
		      }
		}
		
		return response;
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
	
	public static <T> T invokeGetRestServices(String baseUrl, UUID id, Map<String, String> parameters, Class<T> returnType){		
		InputStream inputStream = null;
		try {						
			StringBuilder url = new StringBuilder(baseUrl);
			if (!baseUrl.endsWith("/")){
				url.append("/");				
			}			
			url.append(id);
			
			if (parameters != null){
				url.append("?");
				for(String key : parameters.keySet()){
					url.append(key);
					url.append("=");
					url.append(parameters.get(key));
					url.append("&");
				}
			}
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url.toString());
						
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
			
            String content = httpclient.execute(httpGet, responseHandler);
			
			Gson gson = new Gson();
			gson.fromJson(content, returnType);
			
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
		
		//return null;
	}

	private static boolean callValidationTokenService(String token){		
		InputStream inputStream = null;
		try {
			if (token == null || token.isEmpty()){
				return false;
			}
						
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(loadSystemProperties().getProperty("authorization-service"));
	
			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
			params.add(new BasicNameValuePair("token", token));			
			httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
				
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
	
			if (entity != null) {				
				inputStream = entity.getContent();
				String content = IOUtils.toString(inputStream,"utf-8"); 
				JSONObject jsonObj = new JSONObject(content);
				return jsonObj.getBoolean("isValid");
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
	
	public static Properties loadSystemProperties(){
		Properties properties = new Properties();		
		try {		
			InputStream in = EstampalaTools.class.getClass().getResourceAsStream("/config.properties");
		
			properties.load(in);
			in.close();
		} catch (Exception e) {
			
		}	
		
		return properties;
	}	
	
	public static String getProperty(String name){		
		return loadSystemProperties().getProperty(name);		
	}	
}
