package ru.iFellow.utils;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class AssertionUtil {

    @Step("Проверить, что расы персонажей совпадают")
    public static void assertSpecies(String mortySpecies, String lastCharacterSpecies) {
        Assertions.assertEquals(mortySpecies, lastCharacterSpecies, "Расы персонажей должны совпадать");
    }

    @Step("Проверить, что местоположения персонажей различны")
    public static void assertLocations(String mortyLocation, String lastCharacterLocation) {
        Assertions.assertNotEquals(mortyLocation, lastCharacterLocation, "Местоположения персонажей не должны совпадать");
    }
}


