import controller.PropertyParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PropertyParser propertyParser = new PropertyParser();

        System.out.println(propertyParser.getProperties());
    }
}
