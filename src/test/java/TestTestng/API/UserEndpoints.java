package TestTestng.API;

import io.restassured.response.Response;
import in.testonics.omni.api.restassured;

public class UserEndpoints {

    public static Response createUser(String payload) {
        return restassured.post(payload);
    }

    public static Response readUser(String userName) {
        return restassured.get(userName);
    }

    public static Response updateUser(String userName,String payload){
        return restassured.put(userName,payload);
    }

    public static Response deleteUser(String userName) {
        return restassured.delete(userName);
    }
}
