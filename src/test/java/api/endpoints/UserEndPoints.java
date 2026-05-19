package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    public static Response createUser(User payload){

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
            .when()
                .post(Routes.post_url);

    }
    public static Response readUser(String username){
        return given()
                .pathParam("username",username)
            .when()
                .get(Routes.get_url);
    }
    public static Response updateUser(String username, User payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
            .when()
                .put(Routes.put_url);
    }
    public static Response deleteUser(String username){
        return given()
                .pathParam("username", username)
            .when()
                .delete(Routes.delete_url);
    }
}
