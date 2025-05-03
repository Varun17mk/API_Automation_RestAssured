package CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class RA01_POST_Request_BDD_STYLE {

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

    @Test
    public void postRequest(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com/").basePath("/auth")
                .contentType(ContentType.JSON).when().body(payload).post()
                .then().log().all().statusCode(200);


    }



}
