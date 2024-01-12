package test1;

import apiAutomation.FlightApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import utility.Util;

import java.io.File;

public class FlightApiTest {

    @Test
    public void tc_4_statusCode(){
        RestAssured.baseURI = Util.ENDPOINT;

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.accept(ContentType.JSON).and().baseUri(Util.ENDPOINT).when().get();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200,statusCode);
    }

    @Test
    public void tc_5_contentType(){
        RestAssured.baseURI = Util.ENDPOINT;

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.accept(ContentType.JSON).and().baseUri(Util.ENDPOINT).when().get();

        String contentType = response.getContentType();
        Assert.assertEquals("application/json",contentType);
    }

    @Test
    public void tc_6_responseValidation(){
        RestAssured.baseURI = Util.ENDPOINT;

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.accept(ContentType.JSON).and().baseUri(Util.ENDPOINT).when().get();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
        response.prettyPrint();
        File schema = new File("src/main/resources/flight.json");
        validatableResponse.body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}
