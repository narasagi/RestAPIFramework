package tests;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reporting.Setup;
import restUtils.AssertionUtils;
import tests.pojos.CreatePets;
import tests.pojos.Pets;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class PetsTestScenarios extends CreatePetsAPIs {


    @Test(dataProvider = "petsData")
    public void createPetsAndVerify(CreatePets pets) {
        ExtentTest extentTest = Setup.extentReports.createTest("Test Name: - " + pets.getScenarioID(),
                pets.getScenarioDesc());
        Setup.extentTestThreadLocal.set(extentTest);
        Response response = createPets((Pets) pets);

        if (pets.getExpectedStatusCode() != 200){
            if (pets.getScenarioID().equalsIgnoreCase("CreatePet_DuplicateID")){
                response = createPets((Pets) pets);
                Assert.assertEquals(response.statusCode(),pets.getExpectedStatusCode());
                Assert.assertEquals(response.jsonPath().getString("message"),pets.getExpectedErrorMessage());
            }
        }else {
            Map<String, Object> expectedValueMap = new HashMap<>();
            expectedValueMap.put("id", pets.getId());
            expectedValueMap.put("name", pets.getName());
            expectedValueMap.put("categoryID", pets.getCategoryID());
            expectedValueMap.put("categoryName", pets.getCategoryName());
            expectedValueMap.put("tagID", pets.getTagID());
            expectedValueMap.put("tagName", pets.getTagName());
            expectedValueMap.put("status", pets.getStatus());

            if (pets.getScenarioID().equalsIgnoreCase("CreatePet_WithoutID")){
                expectedValueMap.remove("id");
                AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
            }
        }



    }
    @DataProvider(name = "petsData")
    public Iterator<CreatePets> getCreatePetsData() throws IOException {
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreatePetsTestScenariosData", "sheet1");
        List<CreatePets> petsData = new ArrayList<>();
        for (LinkedHashMap<String,String> data : excelDataAsListOfMap){
            CreatePets pets = getCustomizedPetsData(data);
            petsData.add(pets);
        }
        return petsData.iterator();
    }

    private CreatePets getCustomizedPetsData(LinkedHashMap<String,String> data){
        CreatePets createPets = new CreatePets();
        createPets.setScenarioID(data.get("ScenarioID"));
        createPets.setScenarioDesc(data.get("ScenarioDesc"));
        createPets.setExpectedStatusCode(Integer.parseInt(data.get("ExpectedStatusCode")));

        if (!data.get("ExpectedErrorMessage").equals("NO_DATA"))
            createPets.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));
        if (!data.get("ID").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("Id")));
        if (!data.get("Name").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("Name")));
        if (!data.get("CategoryID").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("CategoryID")));
        if (!data.get("CategoryName").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("CategoryName")));
        if (!data.get("TagID").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("TagID")));
        if (!data.get("TagName").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("TagName")));
        if (!data.get("Status").equalsIgnoreCase("NO_DATA"))
            createPets.setId(Integer.parseInt(data.get("Status")));
        return createPets;
    }

}
