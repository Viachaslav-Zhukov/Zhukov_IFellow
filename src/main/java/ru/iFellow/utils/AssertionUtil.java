package ru.iFellow.utils;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class AssertionUtil {

    @Step("Проверить, что ID пользователя не равен 0")
    public static void assertUserIdNotZero(int userId) {
        Assertions.assertNotEquals(0, userId, "ID пользователя не должен быть равен 0");
    }

    @Step("Проверить, что имена пользователей совпадают")
    public static void assertUserName(String actualName, String expectedName) {
        Assertions.assertEquals(expectedName, actualName, "Имена пользователей должны совпадать");
    }

    @Step("Проверить, что профессии пользователей совпадают")
    public static void assertUserJob(String actualJob, String expectedJob) {
        Assertions.assertEquals(expectedJob, actualJob, "Профессии пользователей должны совпадать");
    }

    @Step("Проверить, что расы персонажей совпадают")
    public static void assertSpecies(String actualSpecies, String expectedSpecies) {
        Assertions.assertEquals(actualSpecies, expectedSpecies, "Расы персонажей должны совпадать");
    }

    @Step("Проверить, что местоположения персонажей различны")
    public static void assertLocations(String actualLocation, String expectedLocation) {
        Assertions.assertNotEquals(actualLocation, expectedLocation, "Местоположения персонажей не должны совпадать");
    }
}


