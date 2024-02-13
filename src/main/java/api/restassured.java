package api;

import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;

import api.URI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class restassured {

    public static Response post(String payload){
        RestAssured.baseURI= URI.base_uri;
        return RestAssured.
                given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).
                when().post(URI.post_uri);
    }

    public static Response get(String userName) {
        RestAssured.baseURI= URI.base_uri;
        return RestAssured.
                given().pathParam("username",userName).
                when().get(URI.get_put_delete_uri);
    }

    public static Response put(String userName,String payload){
        RestAssured.baseURI= URI.base_uri;
        return RestAssured.
                given().contentType(ContentType.JSON).accept(ContentType.JSON).
                pathParam("username",userName).body(payload).
                when().put(URI.get_put_delete_uri);
    }

    public static Response delete(String userName) {
        RestAssured.baseURI= URI.base_uri;
        return RestAssured.
                given().pathParam("username",userName).
                when().delete(URI.get_put_delete_uri);
    }

}
