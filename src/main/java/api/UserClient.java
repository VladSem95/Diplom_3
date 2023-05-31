package api;

import io.restassured.response.ValidatableResponse;
import model.RegistrationPage;

import static io.restassured.RestAssured.given;
public class UserClient extends RestClient{
    private static final String LOGIN_USER_PATH = "api/auth/login";
    private static final String CREATE_USER_PATH = "api/auth/register";
    private static final String DELETE_USER_PATH = "api/auth/user";

    public ValidatableResponse create(String userEmail, String userPassword, String userName) {
        return given()
                .spec(getBaseSpec())
                .body("{"+ '"' + "email" + '"' + ":" + '"' + userEmail + '"' + "," +
                        '"' + "password" + '"' + ":" + '"'  + userPassword + '"' + "," +
                        '"' + "name" + '"' + ":" + '"' + userName + '"' + "}")
                .when()
                .post(CREATE_USER_PATH)
                .then()
                .assertThat();
    }
    public ValidatableResponse delete(String bearerToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(bearerToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .assertThat();
    }
    public ValidatableResponse login(String userEmail, String userPassword) {
        return given()
                .spec(getBaseSpec())
                .body("{"+ '"' + "email" + '"' + ":" + '"' + userEmail + '"' + "," +
                        '"' + "password" + '"' + ":" + '"'  + userPassword + '"' + "}")
                .when()
                .post(LOGIN_USER_PATH)
                .then()
                .assertThat();
    }
}