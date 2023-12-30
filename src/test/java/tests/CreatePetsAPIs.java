package tests;

import io.restassured.response.Response;
import restUtils.RestUtilities;
import tests.pojos.Pets;

import java.util.HashMap;
import java.util.Map;

public class CreatePetsAPIs {

    public Response createPets(Map<String,Object> createPetsPayload){
            String endPoint = (String) Base.readDataFromJsonFile.get("createPetQAEndpoint");
        return  RestUtilities.performPost(endPoint,createPetsPayload, new HashMap<>());
    }

    public Response createPets(Pets createPetsPayload){
        String endPoint = (String) Base.readDataFromJsonFile.get("createPetQAEndpoint");
        return  RestUtilities.performPost(endPoint,createPetsPayload, new HashMap<>());
    }
}
