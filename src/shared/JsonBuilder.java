package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class JsonBuilder {
    private static JsonBuilder instance;

    private JsonBuilder() {

    }

    public static JsonBuilder getInstance() {
        if (instance == null) {
            instance = new JsonBuilder();
        }
        return instance;
    }

    public String createJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(object);
    }
}
