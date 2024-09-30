package ru.iFellow.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import ru.iFellow.api.spec.RickAndMortyRestAssuredClient;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

public class RickAndMortyClient extends RickAndMortyRestAssuredClient {

    @Step("Найти информацию по персонажу Морти Смит")
    public static ValidatableResponse findMorty() {
        return given()
                .spec(getBaseSpec())
                .queryParam("name", "Morty Smith")
                .when()
                .get("/character/")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("results.name", hasItem("Morty Smith"));
    }

    @Step("Получить последний эпизод, где появлялся Морти")
    public static ValidatableResponse getLastEpisodeDetails() {
        ValidatableResponse response = findMorty();
        List<String> episodeUrls = response.extract().path("results[0].episode");
        if (episodeUrls != null && !episodeUrls.isEmpty()) {
            String lastEpisodeUrl = episodeUrls.get(episodeUrls.size() - 1);
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get(lastEpisodeUrl)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
        return null;
    }

    @Step("Получить последнего персонажа из последнего эпизода")
    public static ValidatableResponse getLastCharacterFromLastEpisode() {
        ValidatableResponse episodeResponse = getLastEpisodeDetails();
        if (episodeResponse == null) {
            return null;
        }

        List<String> characterUrls = episodeResponse.extract().path("characters");
        if (characterUrls != null && !characterUrls.isEmpty()) {
            String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get(lastCharacterUrl)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
        return null;
    }

    @Step("Получить данные по местонахождению и расе последнего персонажа")
    public static ValidatableResponse getLastCharacterSpeciesAndLocation() {
        ValidatableResponse lastCharacterResponse = getLastCharacterFromLastEpisode();
        if (lastCharacterResponse == null) {
            return null;
        }

        String lastCharacterUrl = lastCharacterResponse.extract().path("url");
        return given()
                .spec(getBaseSpec())
                .when()
                .get(lastCharacterUrl)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}
