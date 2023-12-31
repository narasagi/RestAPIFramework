package tests;

import com.poiji.bind.Poiji;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;
import tests.pojos.Pets;
import utils.RandomDataGenerator;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PoijiTests extends CreatePetsAPIs {

    @Test(dataProvider = "petsDataPoiji")
    public void createPetsAndVerifyPoiji(Pets pets) {
        String cellValue = pets.getIdValue();
        int size = 6;
        if (cellValue.contains("RandomNumber")){
            if (cellValue.contains("_")){
                size = Integer.parseInt(cellValue.split("_")[1]);
            }
            cellValue = RandomDataGenerator.getRandomNumber(size);
        }
        pets.setIdValue(cellValue);
        Response response = createPets(pets);
        Map<String, Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("id", pets.getId());
        expectedValueMap.put("name", pets.getName());
        expectedValueMap.put("categoryID", pets.getCategoryID());
        expectedValueMap.put("categoryName", pets.getCategoryName());
        expectedValueMap.put("tagID", pets.getTagID());
        expectedValueMap.put("tagName", pets.getTagName());
        expectedValueMap.put("status", pets.getStatus());
        AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);

    }
    @DataProvider(name = "petsDataPoiji")
    public Iterator<Pets> getCreatePetsDataPoiji() throws IOException {
        List<Pets> petsData = Poiji.fromExcel(new File("src/test/resources/testdata/CreateTestDataInExcel.xlsx"),
                Pets.class);
        return petsData.iterator();
    }
    /*public static void main(String[] args) {
        PoijiOptions option = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
        List<PetsPoiji> data = Poiji.fromExcel(new File("src/test/resources/testdata/CreateTestDataInExcel.xlsx"),
                PetsPoiji.class, option);
        System.out.println(data);
    }*/
}
