package ru.iFellow.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        // Указываем путь к файлу конфигурации для тестов
        "file:src/test/resources/ru.iFellow/conf.test"
})
public interface Props extends Config {

    // Параметры пользователя

    // Ключ для имени пользователя
    @Key("name")
    String name(); // Метод для получения имени пользователя из конфигурации

    // Ключ для профессии пользователя
    @Key("job")
    String job(); // Метод для получения профессии пользователя из конфигурации


    // Эндпоинты (пути для API-запросов)

    // Ключ для эндпоинта создания пользователя
    @Key("createUserPath")
    String createUserPath(); // Метод для получения пути создания пользователя

    // Ключ для эндпоинта удаления пользователя
    @Key("deleteUserPath")
    String deleteUserPath(); // Метод для получения пути удаления пользователя

    // Ключ для эндпоинта получения информации о пользователе
    @Key("getUserPath")
    String getUserPath(); // Метод для получения пути получения информации о пользователе


    // Базовый URL для пользователя

    // Ключ для базового URL API работы с пользователем
    @Key("userBaseUrl")
    String userBaseUrl(); // Метод для получения базового URL для запросов о пользователях


    // Базовый URL для API Рик и Морти

    // Ключ для базового URL API Рик и Морти
    @Key("rickAndMortyBaseUrl")
    String rickAndMortyBaseUrl(); // Метод для получения базового URL для API Рик и Морти
}
