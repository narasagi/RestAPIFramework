import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtilities;

import java.util.HashMap;

public class GetTests {

    @Test
    public void readPetByID(){
        String endPoint = "https://petstore.swagger.io/v2/pet/9223372016900012762";
        Response response = RestUtilities.performGet(endPoint,new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }
}
