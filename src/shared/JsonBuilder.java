package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import shared.model.Task;

import java.io.File;
import java.io.IOException;

public class JsonBuilder {
    private final static String PATH_TO_JSON = "staff/task.json";

    public static void toJson(Task task) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(PATH_TO_JSON), task);
    }

    public static String createJsonString(Object object) throws JsonProcessingException {
        //ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(object);
    }
}
