package restUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportsManager;

import java.util.Map;

public class RestUtilities {

    private static RequestSpecification getRequestSpecification(String endpoint, Object requestPayLoad, Map<String,String> headers){
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayLoad);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportsManager.logInfoDetails("Endpoint is" + " " + queryableRequestSpecification.getBaseUri());
        ExtentReportsManager.logInfoDetails("Method is" + " " + queryableRequestSpecification.getMethod());
        ExtentReportsManager.logInfoDetails("Headers are");
        ExtentReportsManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportsManager.logInfoDetails("Request body is");
        ExtentReportsManager.logJson(queryableRequestSpecification.getBody());

    }

    private static void printResponseLogInReport(Response response){
        ExtentReportsManager.logInfoDetails("Status Code is" + " " + response.statusCode());
        ExtentReportsManager.logInfoDetails("Headers are");
        ExtentReportsManager.logHeaders(response.getHeaders().asList());
        ExtentReportsManager.logInfoDetails("Response body is");
        ExtentReportsManager.logJson(response.getBody().prettyPrint());
    }
    public static Response performPost(String endpoint, String requestPayLoad, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecification(endpoint,requestPayLoad,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;

    }
    public static Response performPost(String endpoint, Map<String,Object> requestPayLoad, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecification(endpoint,requestPayLoad,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
    public static Response performPost(String endpoint, Object requestPayLoadFromPojo, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecification(endpoint,requestPayLoadFromPojo,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performGet(String endPoint,Map<String,String>headers){
        return RestAssured.given().log().all()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .get()
                .then().log().all().extract().response();
    }
}
