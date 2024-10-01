package ru.iFellow.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import ru.iFellow.api.spec.RestAssuredClient;
import ru.iFellow.config.Props;
import ru.iFellow.model.CreateUser;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {

    private static final Props props = ConfigFactory.create(Props.class);

    @Step("Создание пользователя")
    public static ValidatableResponse createUser(CreateUser userData) {
        return given()
                .spec(getBaseSpec())
                .body(userData)
                .when()
                .post(props.createUserPath())
                .then().log().all();
    }

    @Step("Удаление пользователя по ID")
    public static void deleteUser(int userId) {
        given()
                .spec(getBaseSpec())
                .pathParam("id", userId)
                .when()
                .delete(props.deleteUserPath())
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Step("Получение пользователя по ID")
    public static void getUserById(int userId) {
        given()
                .spec(getBaseSpec())
                .pathParam("id", userId)
                .when()
                .get(props.getUserPath())
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
