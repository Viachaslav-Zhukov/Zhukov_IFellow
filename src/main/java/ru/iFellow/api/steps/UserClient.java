package ru.iFellow.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import ru.iFellow.api.spec.RestAssuredClient;
import ru.iFellow.config.Props;
import ru.iFellow.model.CreateUser;

import static io.restassured.RestAssured.given;

// Класс, реализующий взаимодействие с API для работы с пользователями
public class UserClient extends RestAssuredClient {

    // Загружаем конфигурацию из интерфейса Props (например, пути к API и базовые настройки)
    private static final Props props = ConfigFactory.create(Props.class);

    // Метод для создания нового пользователя
    @Step("Создание пользователя") // Аннотация для Allure, добавляющая шаги в отчет
    public static ValidatableResponse createUser(CreateUser userData) {
        // Отправляем POST-запрос на создание пользователя с переданными данными (userData)
        return given()
                .spec(getBaseSpec()) // Устанавливаем базовую спецификацию (URI, заголовки и т.д.)
                .body(userData) // Передаем тело запроса с данными пользователя
                .when()
                .post(props.createUserPath()) // Отправляем запрос на создание по указанному пути
                .then().log().all(); // Логируем все детали запроса и ответа
    }

    // Метод для удаления пользователя по его ID
    @Step("Удаление пользователя по ID") // Аннотация для Allure
    public static void deleteUser(int userId) {
        // Отправляем DELETE-запрос для удаления пользователя с указанным ID
        given()
                .spec(getBaseSpec()) // Устанавливаем базовую спецификацию запроса
                .pathParam("id", userId) // Передаем ID пользователя как параметр пути
                .when()
                .delete(props.deleteUserPath()) // Указываем путь для удаления пользователя
                .then().log().all() // Логируем запрос и ответ
                .assertThat() // Проверяем утверждения для ответа
                .statusCode(HttpStatus.SC_NO_CONTENT); // Ожидаем, что статус будет 204 No Content (успешное удаление)
    }

    // Метод для получения пользователя по его ID
    @Step("Получение пользователя по ID") // Аннотация для Allure
    public static void getUserById(int userId) {
        // Отправляем GET-запрос для получения информации о пользователе по ID
        given()
                .spec(getBaseSpec()) // Устанавливаем базовую спецификацию запроса
                .pathParam("id", userId) // Передаем ID пользователя как параметр пути
                .when()
                .get(props.getUserPath()) // Отправляем запрос на получение информации о пользователе
                .then().log().all() // Логируем запрос и ответ
                .assertThat() // Проверяем утверждения для ответа
                .statusCode(HttpStatus.SC_NOT_FOUND); // Ожидаем, что пользователь не будет найден (404 Not Found)
    }
}

