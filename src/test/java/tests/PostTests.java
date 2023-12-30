package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.pojos.Gender;
import tests.pojos.Pets;

import java.io.IOException;
import java.util.Map;

public class PostTests extends CreatePetsAPIs{

    /*@Test
    public void createPet() throws IOException {
        //Creating the data using Map interface
        //Map<String, Object> payLoad = PayLoads.readJsonDataFromMap("0","0","string","doggie","0","string","available");
        //Auto generated data
        //Map<String, Object> payLoad = PayLoads.readAutoDataFromMap();

        //Using POJO class to create custom generated data
        //Pets payLoad = PayLoads.getCreatePetsPayloadFromPojo();
        //Pets pets = new Pets();
       // Pets payLoad = new Pets().toBuilder().name("Narasimha").build();
        //Response response = createPets(payLoad);
        //Assert.assertEquals(response.statusCode(),200);


        //Setting fixed values in POJO class
        //Pets payLoad = new Pets().toBuilder().gender(Gender.male.name()).build(); //another way to set fix values from POJO class using Enum
        Pets payLoad = new Pets();
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(payLoad));

    }*/

    @Test
    public void createPetAndVerifyResponse() throws IOException {
        Pets payLoad = new Pets();
        Response response = createPets(payLoad);

        //First way
        Assert.assertEquals(response.jsonPath().getString("name"),payLoad.getName());


        ObjectMapper objectMapper = new ObjectMapper();
        Pets createPetsResponse = objectMapper.readValue(response.getBody().asString(), Pets.class);
        Assert.assertEquals(createPetsResponse,payLoad);
    }
}
