package CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RA01_POST_Request_NON_BDD_STYLE {

    // HTTP Method -  POST
    // To Create token
    // URL - https://restful-booker.herokuapp.com/auth
    // Base URI - https://restful-booker.herokuapp.com/
    // Base path - /auth
    // Payload {
    //    "username" : "admin",
    //    "password" : "password123"
    //}
    // Verify - 1] TC001 Status code, 2] TC002 Token shd not be null

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @BeforeTest
    public void prePostSetup()
    {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/auth");
    }

    @Test(groups = "Positive")
    public void postRequest(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        response = requestSpecification.when().post();

        // Verification of Status code and token
        String responseString=  response.asString();
        System.out.println(responseString);
        validatableResponse = response.then();
        validatableResponse.log().all().statusCode(200);

    }
    @Test(groups = "Negative")
    public void postRequest2(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        response = requestSpecification.when().post();

        // Verification of Status code and token
        String responseString=  response.asString();
        System.out.println(responseString);
        validatableResponse = response.then();
        validatableResponse.log().all().statusCode(400);

    }


}
