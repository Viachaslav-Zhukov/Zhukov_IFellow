package ru.iFellow.tests;

// Импортируем необходимые классы для работы с тестами и API
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.iFellow.api.steps.RickAndMortyClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;

// Класс для тестирования функциональности API Rick and Morty
public class RickAndMortyTests {

    // Тест для проверки, что запрос к API для персонажа Морти возвращает успешный статус
    @Test
    public void testFindMorty() {
        // Отправляем запрос для получения информации о Морти
        ValidatableResponse response = RickAndMortyClient.findMorty();
        // Проверяем, что статус ответа 200 OK
        response.statusCode(HttpStatus.SC_OK);
    }

    // Тест для получения последнего эпизода, в котором появился Морти
    @Test
    public void testGetLastEpisodeOfMorty() {
        // Получаем данные о последнем эпизоде, где появлялся Морти
        ValidatableResponse episodeResponse = RickAndMortyClient.getLastEpisodeDetails();
        // Проверяем, что ответ не null
        assert episodeResponse != null;
        // Проверяем, что поле "name" эпизода не пустое
        episodeResponse.body("name", not(equalTo("")));
        // Извлекаем имя последнего эпизода
        String lastEpisodeName = episodeResponse.extract().path("name");
        // Выводим информацию о последнем эпизоде
        System.out.println("Последний эпизод, где появлялся Морти: " + lastEpisodeName);
    }

    // Тест для получения последнего персонажа из последнего эпизода
    @Test
    public void testGetLastCharacterFromLastEpisode() {
        // Получаем данные о последнем персонаже
        ValidatableResponse characterResponse = RickAndMortyClient.getLastCharacterFromLastEpisode();
        // Проверяем, что ответ не null и имя персонажа не пустое
        assert characterResponse != null : "Ответ не получен";
        characterResponse.body("name", not(equalTo("")));
        // Выводим информацию о последнем персонаже
        System.out.println("Последний персонаж: " + characterResponse.extract().path("name"));
    }

    // Тест для получения расы и местоположения последнего персонажа
    @Test
    public void testGetLastCharacterSpeciesAndLocation() {
        // Получаем данные о расе и местоположении последнего персонажа
        ValidatableResponse response = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
        // Проверяем, что ответ не null
        assert response != null;
        // Извлекаем расу и местоположение персонажа
        String species = response.extract().path("species");
        String locationName = response.extract().path("location.name");
        // Выводим информацию о расе и местоположении
        System.out.println("Раса последнего персонажа: " + species);
        System.out.println("Местонахождение: " + locationName);
    }

    // Тест для сравнения расы и местоположения Морти с последним персонажем
    @Test
    public void testCompareLastCharacterWithMorty() {
        // Получаем данные о Морти
        ValidatableResponse mortyResponse = RickAndMortyClient.findMorty();
        // Извлекаем расу и местоположение Морти
        String mortySpecies = mortyResponse.extract().path("results[0].species");
        String mortyLocation = mortyResponse.extract().path("results[0].location.name");
        // Получаем данные о последнем персонаже
        ValidatableResponse lastCharacterResponse = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
        // Проверяем, что ответ не null
        assert lastCharacterResponse != null;
        // Извлекаем расу и местоположение последнего персонажа
        String lastCharacterSpecies = lastCharacterResponse.extract().path("species");
        String lastCharacterLocation = lastCharacterResponse.extract().path("location.name");
        // Выводим информацию о Морти и последнем персонаже
        System.out.println("Раса Морти: " + mortySpecies);
        System.out.println("Местоположение Морти: " + mortyLocation);
        System.out.println("Раса последнего персонажа: " + lastCharacterSpecies);
        System.out.println("Местоположение последнего персонажа: " + lastCharacterLocation);
        // Сравниваем расу Морти с расой последнего персонажа
        Assertions.assertEquals(mortySpecies, lastCharacterSpecies, "Расы персонажей должны совпадать");
        // Проверяем, что местоположение Морти и последнего персонажа не совпадают
        Assertions.assertNotEquals(mortyLocation, lastCharacterLocation, "Местоположения персонажей не должны совпадать");
    }

}
