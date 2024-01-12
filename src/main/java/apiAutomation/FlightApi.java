package apiAutomation;

import groovy.json.JsonParser;
import groovy.json.JsonSlurper;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utility.Util;

import java.io.File;

public class FlightApi {


    public void getRequest() {
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
