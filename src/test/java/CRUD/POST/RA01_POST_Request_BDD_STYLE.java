package CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

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

    // Method 1 Using String as payload - Worst way to send as payload, yet if Testcases < 50
//    @Test
//    public void postRequest1(){
//        String payload = "{\n" +
//                "    \"username\" : \"admin\",\n" +
//                "    \"password\" : \"password123\"\n" +
//                "}";
//        RestAssured
//                .given().baseUri("https://restful-booker.herokuapp.com/").basePath("/auth")
//                .contentType(ContentType.JSON).when().body(payload).post()
//                .then().log().all().statusCode(200);
//
//
//    }

    // Method 2 Using Hashmap - Still bad way only for knowledge purpose, yet if Testcases < 100 - 150
    @Test
    public void postRequest2() {

        Map<String, Object> jsonBodyUsingMap = new LinkedHashMap(); //jsonBodyUsingMap - ParentMap
        jsonBodyUsingMap.put("firstname", "Varun");
        jsonBodyUsingMap.put("lastname", "Kulkarni");
        jsonBodyUsingMap.put("totalprice", 111);
        jsonBodyUsingMap.put("depositpaid", true);

        Map<String, Object> bookingDatesMap = new LinkedHashMap(); //bookingDatesMap -  ChildMap
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");

        // Linking ChildMap to ParentMap
        jsonBodyUsingMap.put("bookingdates",bookingDatesMap);


        RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .when().body(jsonBodyUsingMap).post()
                .then().log().all().statusCode(200);
    }


}
