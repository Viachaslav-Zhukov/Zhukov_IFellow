package config;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.fellow.config.AuthConfig;
import ru.fellow.pageObject.AuthorizationPage;

public class WebHooks {
    // Инициализация страницы авторизации.

    public AuthorizationPage authorizationPage;

    // Инициализация конфигурации авторизации.
    public AuthConfig authConfig;

    /*
     * Метод, выполняемый один раз перед каждым тестом.
     * Настраивает WebDriver, открывает страницу авторизации, максимизирует окно браузера и инициализирует страницы.
     */
    @BeforeEach
    public void setUp() {
        // Установка WebDriverManager для Chrome, который автоматически загружает нужный драйвер.
        WebDriverManager.chromedriver().setup();

        // Создание объекта AuthConfig с параметрами для авторизации.
        authConfig = new AuthConfig("AT5", "Qwerty123", "https://edujira.ifellow.ru");

        // Открытие страницы авторизации, используя URL из конфигурации.
        Selenide.open(authConfig.getLoginPageUrl());

        // Максимизация окна браузера.
        WebDriverRunner.getWebDriver().manage().window().maximize();

        // Инициализация страницы авторизации с помощью Selenide, чтобы взаимодействовать с элементами страницы.
        authorizationPage = Selenide.page(AuthorizationPage.class);
    }

    // Метод, выполняемый после каждого теста.
    @AfterEach
    public void tearDown() {
        // Очистка кук и локального хранилища перед закрытием.
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        // Закрытие браузера
        WebDriverRunner.getWebDriver().quit();
    }
}

