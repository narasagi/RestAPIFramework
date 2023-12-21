package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtilities;

import java.util.HashMap;

public class PostTests {

    @Test
    public void createPet(){
        String endPoint = "https://petstore.swagger.io/v2/pet";
        String payLoad = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}'";

        Response response = RestUtilities.performPost(endPoint,payLoad,new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }
}
