package ru.iFellow.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
    SelenideElement usernameField = $x("//input[@id='login-form-username']").as("Поле имени пользователя");
    SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле пароля");
    SelenideElement loginButton = $x("//input[@id='login']").as("Кнопка 'Войти'");

    @Step("Вводим имя пользователя: {username}")
    public void enterUsername(String username) {
        usernameField.setValue(username);
    }

    @Step("Вводим пароль: {password}")
    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Нажимаем кнопку 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Выполняем авторизацию с именем пользователя {username} и паролем {password}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

}
