package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
    SelenideElement usernameField = $x("//input[@id='login-form-username']");
    SelenideElement passwordField = $x("//input[@id='login-form-password']");
    SelenideElement loginButton = $x("//input[@id='login']");

    // Метод для ввода имени пользователя.
    public void enterUsername(String username) {
        usernameField.setValue(username);
    }

    // Метод для ввода пароля.
    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    // Метод для нажатия на кнопку "Войти".
    public void clickLoginButton() {
        loginButton.click();
    }

    // Метод для выполнения полного процесса авторизации.
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
