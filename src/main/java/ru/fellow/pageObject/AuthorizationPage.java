package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class AuthorizationPage {
    // Локатор для поля ввода имени пользователя по его уникальному id
    SelenideElement usernameField = $x("//input[@id='login-form-username']");
    // Локатор для поля ввода пароля по уникальному id
    SelenideElement passwordField = $x("//input[@id='login-form-password']");
    // Локатор для кнопки "Войти" по уникальному id
    SelenideElement loginButton = $x("//input[@id='login']");

    /**
     * Метод для ввода имени пользователя в соответствующее поле.
     *
     * @param username Имя пользователя, которое нужно ввести.
     */
    public void enterUsername(String username) {
        // Вводим имя пользователя в поле ввода
        usernameField.setValue(username);
    }


}
