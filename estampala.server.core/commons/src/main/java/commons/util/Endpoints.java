package commons.util;

import java.io.InputStream;
import java.util.Properties;

public enum Endpoints {
	IS_TOKEN_VALID("validate_token"),
	VALIDATE_TOKEN_FACEBOOK("validate_token_facebook"),
	USERS("users-service"),
	USERS_EMAIL("users-service-email"),
	SHIRT_STYLES("shirtStyles-service"),
	SHIRT_SIZES("shirtSizes-service"),
	THEMES("themes-service"),
	SHIRT_MATERIALS("shirtMaterials-service"),
	SHIRT_COLORS("shirtColors-service"),
	SHIRTS("shirts-service"),
	PRINTS("prints-service"),
	PRINT_FONTS("printFonts-service"),
	SHIRT_PRINT_POSITIONS("shirtPrintPositions-service"),
	PRODUCT("products-service"),
	USERS_EXIST("users-exist-service"),
	SHIRT_STYLES_EXIST("shirtStyles-exist-service"),
	SHIRT_SIZES_EXIST("shirtSizes-exist-service"),
	THEMES_EXIST("themes-exist-service"),
	SHIRT_MATERIALS_EXIST("shirtMaterials-exist-service"),
	SHIRT_COLORS_EXIST("shirtColors-exist-service"),
	SHIRTS_EXIST("shirts-exist-service"),
	PRINTS_EXIST("prints-exist-service"),
	PRINT_FONTS_EXIST("printFonts-exist-service"),
	SHIRT_PRINT_POSITIONS_EXIST("shirtPrintPositions-exist-service"),
	PRODUCT_EXIST("products-exist-service"),
	SHOPPING_CAR("shopping-car-service"),
	SHOPPING_CAR_EXIST("shopping-car-exist-service"),
	PAYMENT("payment-service"),
	PAYMENT_EXIT("payment-exist-service"),
	GIFTCARD("giftcard-service");

	
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
