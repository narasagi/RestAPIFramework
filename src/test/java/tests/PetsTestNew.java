package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;
import tests.pojos.Pets;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PetsTestNew extends CreatePetsAPIs{

    @Test
    public void createPetsAndVerify(){
        Pets request = PayLoads.getCreatePetsPayloadFromPojo();
        Response response = createPets(request);

        Map<String,Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("id",request.getId());
        expectedValueMap.put("name",request.getName()+"KHHJ");
        expectedValueMap.put("categoryID",request.getCategoryID());
        expectedValueMap.put("categoryName",request.getCategoryName());
        expectedValueMap.put("tagID",request.getTagID());
        expectedValueMap.put("tagName",request.getTagName());
        expectedValueMap.put("status",request.getStatus()+"jhghj");

        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValueMap);



        /*System.out.println(response.jsonPath().getInt("id"));

        Optional id = Optional.ofNullable(response.jsonPath().get("id"));
        if (id.isPresent()){
            System.out.println("id is" +id);
        }else {
            System.out.println("Field not found");
        }*/
    }
}
