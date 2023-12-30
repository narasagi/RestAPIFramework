package restUtils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;
import reporting.ExtentReportsManager;
import reporting.Setup;

import java.util.*;

public class AssertionUtils {

    public static void assertExpectedValuesWithJsonPath(Response response, Map<String,Object> expectedValuesMap){
        List<AssertionKeys> actualValuesMap = new ArrayList<>();
        actualValuesMap.add(new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT"));
        boolean allMatched = true;

        Set<String> jsonPaths = expectedValuesMap.keySet();
        for (String jsonPath : jsonPaths){
            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));
            if (actualValue.isPresent()){
                Object value = actualValue.get();
                    if (value.equals(expectedValuesMap.get(jsonPath))){
                        actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),value,"MATCHED"));
                    }else {
                        allMatched = false;
                        actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),value,"NOT_MATCHED"));
                    }
            }else {
                allMatched = false;
                actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),"VALUE_NOT_FOUND","NOT_MATCHED"));
            }
        }
        if (allMatched){
            ExtentReportsManager.logPassDetails("All Assertions are passed. 😊😊😊");
        }else {
            ExtentReportsManager.logFailDetails("All Assertions are not passed. 😒😒😒");
        }
        String[][] finalAssertionMap = actualValuesMap.stream().map(assertions -> new String[]{assertions.getJsonPath(),
                String.valueOf(assertions.getExpectedValue()),String.valueOf(assertions.getActualValue()),
                assertions.getResult()}).toArray(String[][]::new);
        Setup.extentTestThreadLocal.get().info(MarkupHelper.createTable(finalAssertionMap));
    }
}
