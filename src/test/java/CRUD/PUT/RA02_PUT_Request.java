package CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RA02_PUT_Request {
        // To PUT Request [Update request], first we need to create token,create booking and
        // then with token and  booking id we can update or send PUT request and then verify
    // BaseUri - "https://restful-booker.herokuapp.com"

        // So basically 3 requests of 2 diff type need to be made
        // POST Request to create token
        // POST Request to create booking ID
        // PUT Request to update booking

        @Test
        public void put_Request(){
            RequestSpecification r= RestAssured.given();
            r.baseUri("https://restful-booker.herokuapp.com/");

            // Step -1 --> Create Token
            String payloadAuth = "{\n" +
                    "    \"username\" : \"admin\",\n" +
                    "    \"password\" : \"password123\"\n" +
                    "}";
            r.contentType(ContentType.JSON);
            r.basePath("auth");
            r.body(payloadAuth);
            Response response = r.when().post();
            ValidatableResponse vr = response.then().log().all();
            vr.statusCode(200);

            // Extract the token
            String token = response.then().extract().path("token");
            System.out.println("token : "+token);


            // Step -2 --> Create Booking using Post Request and Get ID :
            String payloadPost = "{\n" +
                    "    \"firstname\" : \"James\",\n" +
                    "    \"lastname\" : \"Lanister\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2018-01-01\",\n" +
                    "        \"checkout\" : \"2019-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                    "}";
            r.basePath("booking");
            r.contentType(ContentType.JSON);
            r.body(payloadPost);
            response = r.when().post();
            vr = response.then().log().all();
            vr.statusCode(200);

            // Extract the booking ID
            Integer bookingID = response.then().extract().path("bookingid");
            System.out.println("bookingID : "+bookingID);



            // Step -3 --> Booking ID and token are available :
            // Update firstname - James to name = Varun and lastname - lanister to kulkarni
            String payloadPut = "{\n" +
                    "    \"firstname\" : \"Varun\",\n" +
                    "    \"lastname\" : \"Kulkarni\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2018-01-01\",\n" +
                    "        \"checkout\" : \"2019-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                    "}";

            r.basePath("booking/"+bookingID);
            r.contentType(ContentType.JSON);
            r.cookie("token", token);
            r.body(payloadPut);
            response = r.when().put();
            vr = response.then().log().all();

            //TC - #1
            vr.statusCode(200);
            vr.body("firstname", Matchers.equalTo("Varun"));
            vr.body("lastname",Matchers.equalTo("Kulkarni"));
        }

}
