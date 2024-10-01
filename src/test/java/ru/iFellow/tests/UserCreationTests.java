package ru.iFellow.tests;

import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import ru.iFellow.config.Props;
import ru.iFellow.model.CreateUser;
import ru.iFellow.api.steps.UserClient;
import ru.iFellow.utils.UserDataReader;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("Тесты для создания и удаления пользователя")
public class UserCreationTests {
    private final Props props = ConfigFactory.create(Props.class);
    private ValidatableResponse createResponse;
    private int userId;
    private boolean userDeleted;

    @BeforeEach
    @DisplayName("Подготовка данных перед тестом")
    public void setUp() throws Exception {
        CreateUser userData = UserDataReader.readUserDataFromJson();
        userData.setName(props.name());
        userData.setJob(props.job());

        createResponse = UserClient.createUser(userData);
        String userIdString = createResponse.extract().path("id");
        userId = Integer.parseInt(userIdString);

        System.out.println("Созданный пользователь ID: " + userId);
    }

    @Test
    @DisplayName("Тест успешного создания пользователя")
    public void testCreateUser() {
        createResponse.statusCode(HttpStatus.SC_CREATED);
        Assertions.assertNotEquals(0, userId);
        createResponse.body("name", equalTo(props.name()));
        createResponse.body("job", equalTo(props.job()));

        userDeleted = false;
    }

    @Test
    @DisplayName("Тест удаления пользователя")
    public void testDeleteUser() {
        UserClient.deleteUser(userId);
        userDeleted = true;

        System.out.println("Удаленный пользователь ID: " + userId);
    }

    @Test
    @DisplayName("Тест проверки отсутствия пользователя")
    public void testUserNotExist() {
        UserClient.deleteUser(userId);
        System.out.println("Удаленный пользователь ID: " + userId);
        UserClient.getUserById(userId);
        userDeleted = true;

        System.out.println("Пользователь с ID " + userId + " не существует.");
    }

    @AfterEach
    @DisplayName("Очистка данных после теста")
    public void tearDown() {
        if (userId > 0 && !userDeleted) {
            UserClient.deleteUser(userId);
            System.out.println("Удаленный ID: " + userId);
        }
    }
}
