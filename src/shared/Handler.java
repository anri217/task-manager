package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;

import java.io.IOException;

public interface Handler {

    public void handle(Command command) throws JsonProcessingException, IOException;
}
