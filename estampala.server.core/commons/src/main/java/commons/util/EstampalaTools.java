package commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import commons.responses.ErrorResponse;
import commons.responses.SuccessResponse;

public class EstampalaTools {
	
	public static SuccessResponse isTokenValid(String authorizationHeader){		
		if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
			String[] item = authorizationHeader.trim().split(" ");
    		
    		if (item.length == 2 && item[0].equalsIgnoreCase("token")){    			
    			Map<String, String> parameters = new HashMap<String, String>();
    			parameters.put("token", item[1]);
	    	
    			SuccessResponse res = invokePostRestServices(Endpoints.IS_TOKEN_VALID.getPath(), null, parameters, SuccessResponse.class);
    			if (res != null)
    				return res;						       		        
			}
		}
		
		return null;
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
			
			GsonBuilder builder = new GsonBuilder(); 
            
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
            	   public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            	      return new Date(json.getAsJsonPrimitive().getAsLong());            		
            	   } 
            	});
            
            Gson gson = builder.create();
			
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
	
	public static <T> T invokePostRestServices(Endpoints endpoint, List<String> pathParameters, Map<String, String> parameters, Class<T> returnType){
		return invokePostRestServices(endpoint.getPath(), pathParameters, parameters, returnType);
	}
	
	public static <T> T invokePostRestServices(String baseUrl, List<String> pathParameters, Map<String, String> parameters, Class<T> returnType){		
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
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.toString());
			
			if (parameters != null){
				List<NameValuePair> params = new ArrayList<NameValuePair>(parameters.size());				
				for(String key : parameters.keySet()){
					params.add(new BasicNameValuePair(key, parameters.get(key)));					
				}
				
				httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			}
			
			
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
	
			if (entity != null) {				
				String content = EntityUtils.toString(entity);
				
				Gson gson = new Gson();
				return gson.fromJson(content, returnType);				
			}
			
			return null;
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
}
