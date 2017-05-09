package commons.util;

import java.io.InputStream;
import java.util.Properties;

public enum FeaturesFlag {
	CALIFICAR("calificar"),
	COMPARTIR_CAMISETA("compartir_camiseta"),
	INFO_DETALLADA("info_detallada"),
	RATING("rating"),
	COMPARTIR_ESTAMPA("compartir_estampa"),
	SECURITY("security"),
	AUTH_TWITTER("auth_twitter"),
	VENTAS_USUARIO("ventas_usuario"),
	ADQUIRIR_TARJETA_REGALO("adquirir_tarjeta_regalo"),
	AUTH_FACEBOOK("auth_facebook"),
	TARJETA_CREDITO("tarjeta_credito"),
	TARJETA_REGALO("tarjeta_regalo"),
	ESTAMPAS_RATING("estampas_rating"),
	ESTADO_VENTAS("estado_ventas");

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
