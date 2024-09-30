package ru.iFellow.tests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.api.steps.RickAndMortyClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;

@DisplayName("Тесты для API Rick and Morty")
public class RickAndMortyTests {

    @Test
    @DisplayName("Тест поиска Морти")
    public void testFindMorty() {
        ValidatableResponse response = RickAndMortyClient.findMorty();
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Тест получения последнего эпизода с Морти")
    public void testGetLastEpisodeOfMorty() {
        ValidatableResponse episodeResponse = RickAndMortyClient.getLastEpisodeDetails();
        assert episodeResponse != null;
        episodeResponse.body("name", not(equalTo("")));
        String lastEpisodeName = episodeResponse.extract().path("name");

        System.out.println("Последний эпизод, где появлялся Морти: " + lastEpisodeName);
    }

    @Test
    @DisplayName("Тест получения последнего персонажа из последнего эпизода")
    public void testGetLastCharacterFromLastEpisode() {
        ValidatableResponse characterResponse = RickAndMortyClient.getLastCharacterFromLastEpisode();
        assert characterResponse != null : "Ответ не получен";
        characterResponse.body("name", not(equalTo("")));

        System.out.println("Последний персонаж: " + characterResponse.extract().path("name"));
    }

    @Test
    @DisplayName("Тест получения расы и локации последнего персонажа")
    public void testGetLastCharacterSpeciesAndLocation() {
        ValidatableResponse response = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
        assert response != null;
        String species = response.extract().path("species");
        String locationName = response.extract().path("location.name");

        System.out.println("Раса последнего персонажа: " + species);
        System.out.println("Локация последнего персонажа: " + locationName);
    }

    @Test
    @DisplayName("Тест сравнения расы и локации последнего персонажа с Морти")
    public void testCompareLastCharacterWithMorty() {
        ValidatableResponse mortyResponse = RickAndMortyClient.findMorty();
        String mortySpecies = mortyResponse.extract().path("results[0].species");
        String mortyLocation = mortyResponse.extract().path("results[0].location.name");

        ValidatableResponse lastCharacterResponse = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
        assert lastCharacterResponse != null;
        String lastCharacterSpecies = lastCharacterResponse.extract().path("species");
        String lastCharacterLocation = lastCharacterResponse.extract().path("location.name");

        System.out.println("Раса Морти: " + mortySpecies);
        System.out.println("Местоположение Морти: " + mortyLocation);
        System.out.println("Раса последнего персонажа: " + lastCharacterSpecies);
        System.out.println("Местоположение последнего персонажа: " + lastCharacterLocation);

        Assertions.assertEquals(mortySpecies, lastCharacterSpecies, "Расы персонажей должны совпадать");
        Assertions.assertNotEquals(mortyLocation, lastCharacterLocation, "Локация персонажей не должны совпадать");
    }

}
