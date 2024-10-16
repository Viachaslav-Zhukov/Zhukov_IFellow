package ru.iFellow.hooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.iFellow.config.Props;
import ru.iFellow.utils.LogUtil;

public class WebHooks {
    private static final Props props = ConfigFactory.create(Props.class);

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(false)
        );
    }

    @BeforeEach
    public void setUp() {
        String chromeDriverPath = props.chromeDriverPath();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        try {
            // Проверяем, указан ли путь к драйверу в файле
            if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                WebDriverRunner.setWebDriver(new ChromeDriver(options));
                LogUtil.logToAllure("Драйвер найден по пути: " + chromeDriverPath + ". Используем локальный chromedriver.");
                Selenide.open(props.loginPageUrl());
            } else {
                // Если путь не указан, используем WebDriverManager
                throw new IllegalArgumentException("Путь к chromedriver не указан. Используем WebDriverManager.");
            }
        } catch (Exception e) {
            // Если возникла ошибка, используем WebDriverManager
            LogUtil.logToAllure("Ошибка при использовании локального драйвера: " + e.getMessage());
            LogUtil.logToAllure("Используем WebDriverManager для установки драйвера.");
            WebDriverManager.chromedriver().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
            Selenide.open(props.loginPageUrl());
        }
    }



    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.getWebDriver().quit();
    }
}
