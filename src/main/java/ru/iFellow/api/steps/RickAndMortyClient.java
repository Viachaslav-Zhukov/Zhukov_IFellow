package ru.iFellow.api.steps;

// Импортируем аннотацию Step для использования в Allure отчетах
import io.qameta.allure.Step;
// Импортируем класс ValidatableResponse для работы с ответами RestAssured
import io.restassured.response.ValidatableResponse;
// Импортируем HttpStatus для работы с кодами HTTP-статусов
import org.apache.http.HttpStatus;
// Импортируем базовый клиент для работы с API Rick and Morty
import ru.iFellow.api.spec.RickAndMortyRestAssuredClient;

// Импортируем стандартные коллекции для работы со списками
import java.util.List;

// Статический импорт методов для RestAssured и библиотек для проверок
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

// Класс RickAndMortyClient расширяет RickAndMortyRestAssuredClient, добавляя методы для взаимодействия с API
public class RickAndMortyClient extends RickAndMortyRestAssuredClient {

    // Аннотация @Step используется для создания шагов в Allure отчетах
    @Step("Найти информацию по персонажу Морти Смит")
    public static ValidatableResponse findMorty() {
        // Выполняем GET запрос к API, используя спецификацию и параметр поиска по имени персонажа
        return given()
                // Подключаем базовую спецификацию
                .spec(getBaseSpec())
                // Устанавливаем query-параметр для поиска персонажа по имени "Morty Smith"
                .queryParam("name", "Morty Smith")
                // Отправляем запрос на получение данных о персонаже
                .when()
                .get("/character/")
                // Обрабатываем ответ и проверяем его
                .then()
                // Логируем весь ответ для наглядности
                .log().all()
                // Проверяем, что статус ответа HTTP 200 OK
                .statusCode(HttpStatus.SC_OK)
                // Проверяем, что среди результатов есть персонаж с именем "Morty Smith"
                .body("results.name", hasItem("Morty Smith"));
    }

    // Шаг для получения информации о последнем эпизоде, где появлялся Морти
    @Step("Получить последний эпизод, где появлялся Морти")
    public static ValidatableResponse getLastEpisodeDetails() {
        // Получаем данные о персонаже Морти через метод findMorty
        ValidatableResponse response = findMorty();
        // Извлекаем список эпизодов, в которых появлялся Морти
        List<String> episodeUrls = response.extract().path("results[0].episode");

        // Если список эпизодов не пуст, извлекаем URL последнего эпизода
        if (episodeUrls != null && !episodeUrls.isEmpty()) {
            String lastEpisodeUrl = episodeUrls.get(episodeUrls.size() - 1);
            // Выполняем запрос к последнему эпизоду
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get(lastEpisodeUrl)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
        // Если эпизодов нет, возвращаем null
        return null;
    }

    // Шаг для получения последнего персонажа из последнего эпизода
    @Step("Получить последнего персонажа из последнего эпизода")
    public static ValidatableResponse getLastCharacterFromLastEpisode() {
        // Получаем данные о последнем эпизоде
        ValidatableResponse episodeResponse = getLastEpisodeDetails();
        // Если эпизод не найден, возвращаем null
        if (episodeResponse == null) {
            return null;
        }
        // Извлекаем список URL персонажей из последнего эпизода
        List<String> characterUrls = episodeResponse.extract().path("characters");

        // Если список персонажей не пуст, выполняем запрос к последнему персонажу
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
        // Если список персонажей пуст, возвращаем null
        return null;
    }

    // Шаг для получения данных о расе и местонахождении последнего персонажа
    @Step("Получить данные по местонахождению и расе последнего персонажа")
    public static ValidatableResponse getLastCharacterSpeciesAndLocation() {
        // Получаем данные о последнем персонаже из последнего эпизода
        ValidatableResponse lastCharacterResponse = getLastCharacterFromLastEpisode();
        // Если персонаж не найден, возвращаем null
        if (lastCharacterResponse == null) {
            return null;
        }

        // Извлекаем URL последнего персонажа
        String lastCharacterUrl = lastCharacterResponse.extract().path("url");

        // Выполняем запрос для получения данных о последнем персонаже
        return given()
                .spec(getBaseSpec())
                .when()
                .get(lastCharacterUrl)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

}
