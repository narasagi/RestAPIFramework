package tests;

import utils.JsonUtils;
import java.io.IOException;
import java.util.Map;

public class Base {

    public static Map<String, Object> readDataFromJsonFile;
    static {
        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        try {
            readDataFromJsonFile = JsonUtils.getJsonEndpointFromMap("pets/"+env+"/PetsApiData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
