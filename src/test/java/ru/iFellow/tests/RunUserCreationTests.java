package ru.iFellow.tests;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ru.iFellow/features/user_creation.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ru/iFellow/cucumber/steps/user_creation")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@test")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class RunUserCreationTests {
}
