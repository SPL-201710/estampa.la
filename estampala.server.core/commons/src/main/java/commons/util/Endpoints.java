package commons.util;

import java.io.InputStream;
import java.util.Properties;

public enum Endpoints {
	VALIDATE_TOKEN("validate_token"),
	VALIDATE_TOKEN_FACEBOOK("validate_token_facebook");
	
	private String key;
	private Properties properties;
	
	private Endpoints(String key){
		this.key = key;
	}
	
	public String getPath(){
		return getProperty(key);
	}
	
	private Properties loadSystemProperties(){
		if (properties == null){
			properties = new Properties();		
			try {		
				InputStream in = Endpoints.class.getClass().getResourceAsStream("/config.properties");
			
				properties.load(in);
				in.close();
			} catch (Exception e) {
				
			}	
		}
		
		return properties;
	}	
	
	private String getProperty(String name){		
		return loadSystemProperties().getProperty(name);		
	}
}
