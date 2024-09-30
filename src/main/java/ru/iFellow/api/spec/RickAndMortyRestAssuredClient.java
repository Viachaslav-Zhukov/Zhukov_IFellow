package ru.iFellow.api.spec;

// Импортируем классы для создания спецификаций запросов и логирования запросов в RestAssured
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

// Импортируем библиотеку для работы с конфигурациями через интерфейс Props
import org.aeonbits.owner.ConfigFactory;

// Импортируем интерфейс Props, где хранятся настройки приложения
import ru.iFellow.config.Props;

// Статический импорт, чтобы напрямую использовать тип контента JSON
import static io.restassured.http.ContentType.JSON;

// Объявляем класс RickAndMortyRestAssuredClient, который будет базовым клиентом для запросов к API Rick and Morty
public class RickAndMortyRestAssuredClient  {

    // Создаем статическую переменную props для загрузки конфигураций из Props с помощью ConfigFactory
    private static final Props props = ConfigFactory.create(Props.class);

    // Метод для создания и получения базовой спецификации запроса для API
    protected static RequestSpecification getBaseSpec() {
        // Создаем спецификацию запроса с помощью RequestSpecBuilder
        return new RequestSpecBuilder()
                // Логируем все детали запроса (заголовки, параметры, тело и т.д.)
                .log(LogDetail.ALL)
                // Устанавливаем тип контента как JSON
                .setContentType(JSON)
                // Устанавливаем базовый URL для запросов, используя значение из конфигурации Props
                .setBaseUri(props.rickAndMortyBaseUrl())
                // Строим и возвращаем объект RequestSpecification
                .build();
    }
}



