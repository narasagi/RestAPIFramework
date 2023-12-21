package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtilities {

    public static Response performPost(String endpoint, String requestPayLoad, Map<String,String> headers){

        return RestAssured.given().log().all()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayLoad)
                .post()
                .then().log().all().extract().response();

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
