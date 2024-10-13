package ru.iFellow.tests;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ru.iFellow/features/rick_and_morty.feature")  // Путь к конкретной фиче
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ru/iFellow/cucumber/steps/rick_and_morty")  // Путь к пакету со степами
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@test")  // Фильтрация по тегу
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)

public class RunRickAndMortyTests {
}
