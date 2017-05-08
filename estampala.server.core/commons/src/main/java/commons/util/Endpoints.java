package commons.util;

import java.io.InputStream;
import java.util.Properties;

public enum Endpoints {
	IS_TOKEN_VALID("is_token_valid"),
	VALIDATE_TOKEN_FACEBOOK("validate_token_facebook"),
	USERS("users-service"),
	SHIRT_STYLES("shirtStyles-service"),
	SHIRT_SIZES("shirtSizes-service"),
	THEMES("themes-service"),
	SHIRT_MATERIALS("shirtMaterials-service"),
	SHIRT_COLORS("shirtColors-service"),
	SHIRTS("shirts-service"),
	PRINTS("prints-service"),
	PRINT_FONTS("printFonts-service"),
	SHIRT_PRINT_POSITIONS("shirtPrintPositions-service"),
	PRODUCT("products-service");
	
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
