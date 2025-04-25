import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class RATestNg {

    @Test
    public void getRequest_Positive() {
        RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com/ping")
                .when().get()
                .then().log().all().statusCode(201);
    }

    @Test
    public void getRequest_Negative() {
        RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com/ping")
                .when().get()
                .then().log().all().statusCode(200);
    }
}
