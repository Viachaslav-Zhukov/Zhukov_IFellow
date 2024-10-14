package ru.iFellow.cucumber.steps.user_creation;

import io.cucumber.java.After;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.apache.hc.core5.http.HttpStatus;
import ru.iFellow.api.steps.UserClient;
import ru.iFellow.config.Props;
import ru.iFellow.model.CreateUser;
import ru.iFellow.utils.AssertionUtil;
import ru.iFellow.utils.LogUtil;
import ru.iFellow.utils.UserDataReader;

public class UserCreationSteps {
    private final Props props = ConfigFactory.create(Props.class);
    private ValidatableResponse createResponse;
    private int userId;
    private boolean userDeleted;

    @Когда("я подготовил и отправил данные для создания нового пользователя")
    public void prepareDataForNewUser() throws Exception {
        CreateUser userData = UserDataReader.readUserDataFromJson();
        userData.setName(props.name());
        userData.setJob(props.job());

        createResponse = UserClient.createUser(userData);
        String userIdString = createResponse.extract().path("id");
        userId = Integer.parseInt(userIdString);
        LogUtil.logToAllure("Созданный пользователь ID: " + userId);
    }

    @Тогда("я получаю статус создания пользователя '201' и ответ содержит правильные данные")
    public void verifyStatusAndResponseData() {

        createResponse.statusCode(HttpStatus.SC_CREATED);
        AssertionUtil.assertUserIdNotZero(userId);

        String responseName = createResponse.extract().path("name");
        String responseJob = createResponse.extract().path("job");
        LogUtil.logToAllure("Проверяем данные пользователя: имя = " + responseName + ", работа = " + responseJob);

        AssertionUtil.assertUserName(responseName, props.name());
        AssertionUtil.assertUserJob(responseJob, props.job());
        userDeleted = false;
    }

    @Тогда("я удаляю созданного пользователя и проверяю статус успешного удаления")
    public void deleteUserAndVerifyStatus() {

        UserClient.deleteUser(userId);
        userDeleted = true;
        LogUtil.logToAllure("Удаленный пользователь ID: " + userId);
    }

    @Тогда("я проверяю отсутствие пользователя по ID, после удаления и статус ответа")
    public void checkUserNotExist() {

        UserClient.deleteUser(userId);
        userDeleted = true;
        LogUtil.logToAllure("Удаленный пользователь ID: " + userId);

        UserClient.getUserById(userId);
        LogUtil.logToAllure("Пользователь с ID " + userId + " не существует.");
    }

    @After
    public void tearDown() {
        if (userId > 0 && !userDeleted) {
            UserClient.deleteUser(userId);
            LogUtil.logToAllure("Удаленный ID: " + userId);
        }
    }

}
