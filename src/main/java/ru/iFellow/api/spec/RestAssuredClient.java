package ru.iFellow.api.spec;

// Импортируем необходимые классы и библиотеки из RestAssured для настройки запросов
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

// Импортируем класс для конфигурации с помощью библиотеки Owner
import org.aeonbits.owner.ConfigFactory;

// Импортируем интерфейс Props, содержащий параметры конфигурации
import ru.iFellow.config.Props;

// Статический импорт, который позволяет обращаться к JSON-контенту напрямую
import static io.restassured.http.ContentType.JSON;

// Объявляем класс RestAssuredClient, который будет базовым клиентом для запросов
public class RestAssuredClient {

    // Создаем статическую переменную props, которая загружает конфигурацию из Props с помощью ConfigFactory
    private static final Props props = ConfigFactory.create(Props.class);

    // Метод для получения базовой спецификации запросов
    protected static RequestSpecification getBaseSpec() {
        // Возвращаем объект RequestSpecification, который настраивается с помощью RequestSpecBuilder
        return new RequestSpecBuilder()
                // Логируем все детали запросов (заголовки, параметры, тело)
                .log(LogDetail.ALL)
                // Устанавливаем тип контента как JSON для всех запросов
                .setContentType(JSON)
                // Устанавливаем базовый URL для запросов из конфигурации props
                .setBaseUri(props.userBaseUrl())
                // Завершаем настройку и возвращаем готовую спецификацию запроса
                .build();
    }
}


