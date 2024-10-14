package ru.iFellow.cucumber.steps.rick_and_morty;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;
import ru.iFellow.api.steps.RickAndMortyClient;
import ru.iFellow.utils.AssertionUtil;
import ru.iFellow.utils.LogUtil;

public class RickAndMortySteps {
    private ValidatableResponse response;
    private ValidatableResponse episodeResponse;
    private ValidatableResponse speciesAndLocationResponse;

    @Когда("я отправляю запрос на получение информации о персонаже Морти Смит")
    public void sendRequestToGetInformationAboutMortySmith() {

        response = RickAndMortyClient.findMorty();
    }

    @Тогда("я должен получить информацию о персонаже Морти Смит")
    public String shouldReceiveInformationAboutMortySmith() {

        String mortyName = response.extract().path("results[0].name");
        LogUtil.logToAllure("Имя найденного персонажа: " + mortyName);
        return mortyName;
    }

    @Когда("я отправляю запрос на получение последнего эпизода с Морти")
    public void sendRequestToGetLastEpisode() {

        episodeResponse = RickAndMortyClient.getLastEpisodeDetails();
    }

    @Тогда("я должен получить последний эпизод, где появлялся Морти")
    public String shouldGetLastEpisodeOfMorty() {

        String lastEpisodeName = episodeResponse.extract().path("name");
        LogUtil.logToAllure("Последний эпизод, где появлялся Морти: " + lastEpisodeName);
        return lastEpisodeName;
    }

    @Тогда("я должен получить последнего персонажа из последнего эпизода")
    public void shouldGetLastCharacterFromLastEpisode() {

        ValidatableResponse lastCharacterResponse = RickAndMortyClient.getLastCharacterFromLastEpisode();

        if (lastCharacterResponse != null) {
            String lastCharacterName = lastCharacterResponse.extract().path("name");
            LogUtil.logToAllure("Последний персонаж в эпизоде: " + lastCharacterName);
        } else {
            LogUtil.logToAllure("Не удалось получить последнего персонажа: lastCharacterResponse равен null");
        }
    }


    @Когда("я отправляю запрос на получение данных по расе и местоположению последнего персонажа")
    public void sendRequestToGetLastCharacterSpeciesAndLocation() {

        speciesAndLocationResponse = RickAndMortyClient.getLastCharacterSpeciesAndLocation();
    }

    @Тогда("я должен получить расу и местоположение последнего персонажа")
    public void shouldReceiveLastCharacterSpeciesAndLocation() {

        String species = speciesAndLocationResponse.extract().path("species");
        String locationName = speciesAndLocationResponse.extract().path("location.name");

        LogUtil.logToAllure("Раса последнего персонажа: " + species);
        LogUtil.logToAllure("Местоположение последнего персонажа: " + locationName);
    }

    @Тогда("я должен сравнить расы и местоположения Морти Смит и последнего персонажа")
    public void shouldCompareMortyAndLastCharacter() {

        String mortySpecies = response.extract().path("results[0].species");
        String mortyLocation = response.extract().path("results[0].location.name");

        LogUtil.logToAllure("Раса Морти: " + mortySpecies);
        LogUtil.logToAllure("Местоположение Морти: " + mortyLocation);

        String lastCharacterSpecies = speciesAndLocationResponse.extract().path("species");
        String lastCharacterLocation = speciesAndLocationResponse.extract().path("location.name");

        LogUtil.logToAllure("Раса последнего персонажа: " + lastCharacterSpecies);
        LogUtil.logToAllure("Местоположение последнего персонажа: " + lastCharacterLocation);

        AssertionUtil.assertSpecies(mortySpecies, lastCharacterSpecies);
        AssertionUtil.assertLocations(mortyLocation, lastCharacterLocation);
    }

}
