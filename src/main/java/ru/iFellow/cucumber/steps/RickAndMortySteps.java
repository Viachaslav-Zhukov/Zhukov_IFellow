package ru.iFellow.cucumber.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;
import ru.iFellow.api.steps.RickAndMortyClient;
import ru.iFellow.utils.LogUtil;

public class RickAndMortySteps {

    private ValidatableResponse response; // Ответ на запрос о персонаже
    private ValidatableResponse episodeResponse; // Ответ на запрос о последнем эпизоде
    private ValidatableResponse lastCharacterResponse; // Ответ на запрос о последнем персонаже
    private ValidatableResponse speciesAndLocationResponse; // Ответ на запрос о расе и местоположении последнего персонажа
    private ValidatableResponse mortyResponse; // Ответ на запрос о Морти

    @Когда("я отправляю запрос на получение информации о персонаже Морти Смит")
    public void sendRequestToGetInformationAboutMortySmith() {
        // Используем клиента API для поиска персонажа Морти Смит
        response = RickAndMortyClient.findMorty();
    }

    @Тогда("я должен получить информацию о персонаже Морти Смит")
    public String shouldReceiveInformationAboutMortySmith() {
        // Извлекаем имя первого персонажа из ответа
        String mortyName = response.extract().path("results[0].name");
        LogUtil.logToAllure("Имя найденного персонажа: " + mortyName); // Логируем в Allure и выводим в терминал
        return mortyName; // Возвращаем имя для дальнейшей проверки
    }

    @Когда("я отправляю запрос на получение последнего эпизода с Морти")
    public void sendRequestToGetLastEpisode() {
        // Запрос для получения последнего эпизода с Морти
        episodeResponse = RickAndMortyClient.getLastEpisodeDetails();
    }

    @Тогда("я должен получить последний эпизод, где появлялся Морти")
    public String shouldGetLastEpisodeOfMorty() {
        // Извлекаем имя последнего эпизода из ответа
        String lastEpisodeName = episodeResponse.extract().path("name");
        LogUtil.logToAllure("Последний эпизод, где появлялся Морти: " + lastEpisodeName); // Логируем в Allure
        return lastEpisodeName; // Возвращаем имя для дальнейшей проверки
    }

    @Тогда("я должен получить последнего персонажа из последнего эпизода")
    public void shouldGetLastCharacterFromLastEpisode() {
        // Получаем информацию о последнем персонаже из последнего эпизода
        lastCharacterResponse = RickAndMortyClient.getLastCharacterFromLastEpisode();
        String lastCharacterName = lastCharacterResponse.extract().path("name");
        LogUtil.logToAllure("Последний персонаж в эпизоде: " + lastCharacterName); // Логируем в Allure
    }

    @Когда("я отправляю запрос на получение данных по расе и местоположению последнего персонажа")
    public void sendRequestToGetLastCharacterSpeciesAndLocation() {
        // Запрос для получения данных о расе и местоположении последнего персонажа
        speciesAndLocationResponse = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
    }

    @Тогда("я должен получить расу и местоположение последнего персонажа")
    public void shouldReceiveLastCharacterSpeciesAndLocation() {
        // Извлекаем расу и местоположение из ответа
        String species = speciesAndLocationResponse.extract().path("species");
        String locationName = speciesAndLocationResponse.extract().path("location.name");

        LogUtil.logToAllure("Раса последнего персонажа: " + species); // Логируем в Allure
        LogUtil.logToAllure("Местоположение последнего персонажа: " + locationName); // Логируем в Allure
    }

    @Когда("я отправляю запрос на получение расы и местоположения Морти Смит и последнего персонажа")
    public void sendRequestToCompareMortyWithLastCharacter() {
        // Запрос для получения информации о Морти
        mortyResponse = RickAndMortyClient.findMorty();
        // Запрос для получения информации о последнем персонаже
        lastCharacterResponse = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
    }

    @Тогда("я должен получить и сравнить расы и местоположения Морти Смит и последнего персонажа")
    public void shouldGetComparisonResults() {
        // Сохраняем результаты в ответах
        String mortySpecies = mortyResponse.extract().path("results[0].species");
        String mortyLocation = mortyResponse.extract().path("results[0].location.name");

        // Логируем данные о Морти
        LogUtil.logToAllure("Раса Морти: " + mortySpecies);
        LogUtil.logToAllure("Местоположение Морти: " + mortyLocation);

        // Извлекаем данные о последнем персонаже
        String lastCharacterSpecies = lastCharacterResponse.extract().path("species");
        String lastCharacterLocation = lastCharacterResponse.extract().path("location.name");

        // Логируем данные о последнем персонаже
        LogUtil.logToAllure("Раса последнего персонажа: " + lastCharacterSpecies);
        LogUtil.logToAllure("Местоположение последнего персонажа: " + lastCharacterLocation);

        // Сообщение о завершении сравнения
        LogUtil.logToAllure("Сравнение завершено.");
    }
}
