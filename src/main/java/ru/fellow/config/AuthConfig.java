package ru.fellow.config;

//Класс AuthConfig хранит данные конфигурации для авторизации.
public class AuthConfig {
    // Поля конфигурации для авторизации, инициализированные при создании объекта.
    private final String username;

    private final String password;

    private final String loginPageUrl;

    // Конструктор для инициализации полей конфигурации.
    public AuthConfig(String username, String password, String loginPageUrl) {
        this.username = username;

        this.password = password;

        this.loginPageUrl = loginPageUrl;
    }

    /*
     * Метод для получения имени пользователя.
     * @return username — имя пользователя.
     */
    public String getUsername() {
        return username;
    }

    /*
     * Метод для получения пароля пользователя.
     * @return password — пароль пользователя.
     */
    public String getPassword() {
        return password;
    }

    /*
     * Метод для получения URL страницы входа.
     * @return loginPageUrl — URL страницы авторизации.
     */
    public String getLoginPageUrl() {
        return loginPageUrl;
    }
}
