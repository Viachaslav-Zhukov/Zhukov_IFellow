package ru.iFellow.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import ru.iFellow.api.spec.RickAndMortyRestAssuredClient;
import ru.iFellow.config.Props;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

public class RickAndMortyClient extends RickAndMortyRestAssuredClient {

    @Step("Найти информацию по персонажу Морти Смит")
    public static ValidatableResponse findMorty() {
        Props props = ConfigFactory.create(Props.class);

        return given()
                .spec(getBaseSpec())
                .queryParam("name", props.mortyName())
                .when()
                .get(props.characterEndpoint())
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("results.name", hasItem(props.mortyName()));
    }


    @Step("Получить последний эпизод, где появлялся Морти")
    public static ValidatableResponse getLastEpisodeDetails() {
        ValidatableResponse mortyResponse = findMorty();

        List<String> episodeUrls = mortyResponse.extract().path("results[0].episode");
        if (episodeUrls != null && !episodeUrls.isEmpty()) {
            String lastEpisodeUrl = episodeUrls.get(episodeUrls.size() - 1);

            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get(lastEpisodeUrl)
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK);
        }

        return null;
    }

    @Step("Получить последнего персонажа из последнего эпизода с Морти")
    public static ValidatableResponse getLastCharacterFromLastEpisode() {
        ValidatableResponse lastEpisodeResponse = getLastEpisodeDetails();

        if (lastEpisodeResponse == null) {
            return null;
        }

        List<String> characterUrls = lastEpisodeResponse.extract().path("characters");
        if (characterUrls != null && !characterUrls.isEmpty()) {
            String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);

            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get(lastCharacterUrl)
                    .then()
                    .log().all()
                    .assertThat()
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
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
