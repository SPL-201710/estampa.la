package commons.util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

	  @Override
	  public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
	      
	      try {
	          return new Date(Long.valueOf(element.getAsJsonPrimitive().getAsString()));
	      } catch (JsonParseException exp) {
	          System.err.println("Failed to parse Date:");
	          return null;
	      }
	   }

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormatAsString = format.format(src);
	    return new JsonPrimitive(dateFormatAsString);
	}
}
