package TestngTest.API;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import io.restassured.response.Response;
import Support.hooksAPI;
import utils.jsonUtils;

/**
 * Perform API Testing for the services using CRUD Design Pattern
 */
public class TestEndpoints extends hooksAPI {

    jsonUtils jsonutil = new jsonUtils();

    @Test(priority = 1, description = "Creating the test user")
    public void testPostUser() {
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        bodyParams.put("id", userPayload.getUserId());
        bodyParams.put("username", userPayload.getUserName());
        bodyParams.put("firstName", userPayload.getFirstName());
        bodyParams.put("lastName", userPayload.getLastName());
        bodyParams.put("email", userPayload.getEmail());
        bodyParams.put("password", userPayload.getPassword());
        bodyParams.put("phone", userPayload.getPhone());
        bodyParams.put("userStatus", 0);
        String payload = new Gson().toJson(bodyParams);
        logger.info("*************************{POST}***************************************");
        Response response = UserEndpoints.createUser(payload);
        System.out.println("Response as string : " + response.asString());
        response.then().log().all();
        System.out.println(jsonutil.jsonToMap("C:\\Projects\\RestAssured\\data\\expected.json"));
        System.out.println("Response : " + response.getBody().toString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getStatusLine().contains("OK"));
        logger.info("**********" + this.userPayload.getUserName() + " is created ************");
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("*************************{GET}****************************************");
        Response response = UserEndpoints.readUser(this.userPayload.getUserName());
        response.then().log().body().statusCode(200);
        logger.info("************  " + this.userPayload.getUserName() + " is fetched **********");
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        bodyParams.put("id", userPayload.getUserId());
        bodyParams.put("username", userPayload.getUserName());
        /**
         * update starts from here based on existing user name
         */
        bodyParams.put("firstName", userPayload.getFirstName() + " is my first name");
        bodyParams.put("lastName", userPayload.getLastName() + " is my last name");
        bodyParams.put("email", userPayload.getEmail() + " is my email");
        bodyParams.put("password", userPayload.getPassword() + " is my password");
        bodyParams.put("phone", userPayload.getPhone() + " is my phone number");
        bodyParams.put("userStatus", 1);
        String payload = new Gson().toJson(bodyParams);
        logger.info("*************************{UPDATE}************************************");
        Response response = UserEndpoints.updateUser(this.userPayload.getUserName(), payload);
        response.then().log().body().statusCode(200);
        Response afterUpdateResponse = UserEndpoints.readUser(this.userPayload.getUserName());
        afterUpdateResponse.then().log().body().statusCode(200);
        logger.info("*********  " + this.userPayload.getUserName() + " is updated ************");
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        logger.info("*************************{DELETE}************************************");
        Response response = UserEndpoints.deleteUser(this.userPayload.getUserName());
        response.then().log().body().statusCode(200);
        logger.info("********  " + this.userPayload.getUserName() + " is deleted *************");
    }

}
