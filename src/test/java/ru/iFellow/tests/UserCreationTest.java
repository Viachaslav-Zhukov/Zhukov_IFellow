package ru.iFellow.tests;

import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.iFellow.config.Props;
import ru.iFellow.model.CreateUser;
import ru.iFellow.api.steps.UserClient;
import ru.iFellow.utils.UserDataReader;

import static org.hamcrest.Matchers.equalTo;

public class UserCreationTest {

    private final Props props = ConfigFactory.create(Props.class);  // Инициализация конфигурации
    private ValidatableResponse createResponse; // Переменная для хранения ответа на создание пользователя
    private int userId; // Переменная для хранения ID созданного пользователя
    private boolean userDeleted; // Переменная для отслеживания статуса удаления пользователя

    @BeforeEach
    public void setUp() throws Exception {
        // Читаем данные из JSON-файла (путь уже определён в JsonReader)
        CreateUser userData = UserDataReader.readUserDataFromJson(); // Локальная переменная

        // Получаем значения из файла конфигурации

        userData.setName(props.name());  // Устанавливаем имя из файла конфигурации
        userData.setJob(props.job());    // Устанавливаем должность из файла конфигурации

        // Создаем пользователя и сохраняем ответ
        createResponse = UserClient.createUser(userData);

        String userIdString = createResponse.extract().path("id"); // Извлекаем как строку
        userId = Integer.parseInt(userIdString); // Преобразуем строку в целое число
        System.out.println("Созданный пользователь ID: " + userId);
    }

    @Test
    public void testCreateUser() {
        // Проверяем, что ответ имеет статус 201
        createResponse.statusCode(HttpStatus.SC_CREATED);

        // Проверяем, что ID не равен нулю
        Assertions.assertNotEquals(0, userId); // Проверяем, что ID не равен нулю

        // Проверяем, что данные совпадают с ожидаемыми
        createResponse.body("name", equalTo(props.name())); // Проверка на имя из конфигурации
        createResponse.body("job", equalTo(props.job()));   // Проверка на должность из конфигурации
        // Устанавливаем статус удаления в true после успешной проверки
        userDeleted = false; // Обозначаем, что пользователь не удален
    }

    @Test
    public void testDeleteUser() {
        // Удаляем пользователя по ID
        UserClient.deleteUser(userId);

        // Устанавливаем статус удаления в true
        userDeleted = true; // Обозначаем, что пользователь был удален

        // Выводим ID удаленного пользователя
        System.out.println("Удаленный пользователь ID: " + userId);
    }

    @Test
    public void testUserNotExist() {
        // Удаляем пользователя по ID
        UserClient.deleteUser(userId);
        // Выводим ID удаленного пользователя
        System.out.println("Удаленный пользователь ID: " + userId);
        // Проверяем, что пользователь больше не существует, ожидаем статус 404
        UserClient.getUserById(userId);

        // Устанавливаем статус удаления в true
        userDeleted = true; // Обозначаем, что пользователь был удален

        // Выводим сообщение в терминал о том, что пользователь с указанным ID не существует
        System.out.println("Пользователь с ID " + userId + " не существует (статус 404).");
    }

    @AfterEach
    public void tearDown() {
        if (userId > 0 && !userDeleted) { // Проверяем, был ли пользователь удален
            UserClient.deleteUser(userId); // Удаляем пользователя, не проверяя статус
            System.out.println("Удаленный ID: " + userId);
        }
    }
}
