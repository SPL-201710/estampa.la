package commons.util;

import java.io.InputStream;
import java.util.Properties;

public enum FeaturesFlag {
	CALIFICAR("calificar"),
	COMPARTIR_CAMISETA("compartir_camiseta"),
	INFO_DETALLADA("info_detallada"),
	RATING("rating"),
	COMPARTIR_ESTAMPA("compartir_estampa"),
	SECURITY("security");
	
	private String key;	
	private Properties properties;
	
	private FeaturesFlag(String key){
		this.key = key;
	}
	
	public boolean isActive(){
		return getProperty(key);
	}
	
	private Properties loadSystemProperties(){
		if (properties == null){
			properties = new Properties();		
			try {		
				InputStream in = Endpoints.class.getClass().getResourceAsStream("/features.properties");
			
				properties.load(in);
				in.close();
			} catch (Exception e) {
				
			}	
		}
		
		return properties;
	}	
	
	private boolean getProperty(String name){		
		return Boolean.parseBoolean(loadSystemProperties().getProperty(name));		
	}
}
