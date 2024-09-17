package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
    // Локатор для поля ввода имени пользователя по его уникальному id.

    SelenideElement usernameField = $x("//input[@id='login-form-username']");

    // Локатор для поля ввода пароля по-уникальному id.
    SelenideElement passwordField = $x("//input[@id='login-form-password']");

    // Локатор для кнопки "Войти" по-уникальному id.
    SelenideElement loginButton = $x("//input[@id='login']");

    // Метод для ввода имени пользователя в соответствующее поле.
    public void enterUsername(String username) {
        // Вводим имя пользователя в поле ввода
        usernameField.setValue(username);
    }

    // Метод для ввода пароля в соответствующее поле.
    public void enterPassword(String password) {
        // Вводим пароль в поле ввода
        passwordField.setValue(password);
    }

    // Метод для нажатия на кнопку "Войти".
    public void clickLoginButton() {
        // Нажимаем на кнопку "Войти"
        loginButton.click();
    }

    // Метод для выполнения полного процесса авторизации.
    public void login(String username, String password) {
        // Вводим имя пользователя.
        enterUsername(username);

        // Вводим пароль.
        enterPassword(password);

        // Нажимаем на кнопку "Войти".
        clickLoginButton();
    }
}
