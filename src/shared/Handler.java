package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;

public interface Handler {

    public void handle(Command command) throws JsonProcessingException;
}
