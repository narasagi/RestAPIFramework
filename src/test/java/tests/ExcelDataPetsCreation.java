package tests;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;
import tests.pojos.Pets;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class ExcelDataPetsCreation extends CreatePetsAPIs {

    @Test(dataProvider = "petsData")
    public void createPetsAndVerify(Pets pets) {
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
    @DataProvider(name = "petsData")
    public Iterator<Pets> getCreatePetsData() throws IOException {
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreateTestDataInExcel", "sheet1");
        List<Pets> petsData = new ArrayList<>();
        for (LinkedHashMap<String,String> data : excelDataAsListOfMap){
            Pets pets = Pets.builder()
                    .id(Integer.parseInt(data.get("Id")))
                    .name(data.get("Name"))
                    .categoryID(data.get("CatagoryID"))
                    .categoryName(data.get("CatagoryName"))
                    .tagID(Integer.parseInt(data.get("TagID")))
                    .tagName(data.get("TagName"))
                    .status(data.get("Status"))
                    .build();
            petsData.add(pets);
        }
        return petsData.iterator();
    }
    /*public static void main(String[] args) throws IOException {
        System.out.println(ExcelUtils.getExcelDataAsListOfMap("CreateTestDataInExcel","sheet1"));
    }*/
}
