package ru.iFellow.tests;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.api.steps.RickAndMortyClient;

@DisplayName("Тесты для API Rick and Morty")
public class RickAndMortyTests {

    @Test
    @DisplayName("Тест поиска Морти")
    @Description("Проверка успешного поиска персонажа Морти Смит с проверкой того, что имя не пустое")
    public void testFindMorty() {
        ValidatableResponse response = RickAndMortyClient.findMorty();

        String mortyName = response.extract().path("results[0].name");
        Assertions.assertFalse(mortyName.isEmpty(), "Имя Морти не должно быть пустым");
    }

    @Test
    @DisplayName("Тест получения последнего эпизода с Морти")
    @Description("Проверка получения последнего эпизода с участием Морти и того, что название эпизода не пустое")
    public void testGetLastEpisodeOfMorty() {
        ValidatableResponse episodeResponse = RickAndMortyClient.getLastEpisodeDetails();
        Assertions.assertNotNull(episodeResponse);

        String lastEpisodeName = episodeResponse.extract().path("name");
        Assertions.assertFalse(lastEpisodeName.isEmpty(), "Имя последнего эпизода не должно быть пустым");
    }

    @Test
    @DisplayName("Тест получения последнего персонажа из последнего эпизода с Морти")
    @Description("Проверка получения последнего персонажа из последнего эпизода с Морти и того, что имя персонажа не пустое")
    public void testGetLastCharacterFromLastEpisode() {
        ValidatableResponse lastCharacterResponse = RickAndMortyClient.getLastCharacterFromLastEpisode();
        Assertions.assertNotNull(lastCharacterResponse);

        String lastCharacterName = lastCharacterResponse.extract().path("name");
        Assertions.assertFalse(lastCharacterName.isEmpty(), "Имя последнего персонажа не должно быть пустым");
    }

    @Test
    @DisplayName("Тест получения данных по расе и местоположению последнего персонажа")
    @Description("Проверка получения расы и местоположения последнего персонажа и того, что данные не пустые")
    public void testGetLastCharacterSpeciesAndLocation() {
        ValidatableResponse response = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
        Assertions.assertNotNull(response);

        String species = response.extract().path("species");
        String locationName = response.extract().path("location.name");

        Assertions.assertFalse(species.isEmpty(), "Раса персонажа не должна быть пустой");
        Assertions.assertFalse(locationName.isEmpty(), "Местоположение персонажа не должно быть пустым");
    }

    @Test
    @DisplayName("Сравнение расы и местоположения Морти Смит с последним персонажем")
    @Description("Сравнение расы и местоположения Морти Смит с последним персонажем для выявления различий")
    public void testCompareLastCharacterWithMorty() {
        ValidatableResponse mortyResponse = RickAndMortyClient.findMorty();
        String mortySpecies = mortyResponse.extract().path("results[0].species");
        String mortyLocation = mortyResponse.extract().path("results[0].location.name");

        ValidatableResponse lastCharacterResponse = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
        Assertions.assertNotNull(lastCharacterResponse);

        String lastCharacterSpecies = lastCharacterResponse.extract().path("species");
        String lastCharacterLocation = lastCharacterResponse.extract().path("location.name");

        Assertions.assertEquals(mortySpecies, lastCharacterSpecies, "Расы персонажей должны совпадать");
        Assertions.assertNotEquals(mortyLocation, lastCharacterLocation, "Местоположения персонажей не должны совпадать");
    }
}
