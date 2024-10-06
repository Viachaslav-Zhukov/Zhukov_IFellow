package ru.iFellow.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Condition;

public class MainPage {

    SelenideElement profileIcon = $x("//img[@alt='Пользовательский профиль для AT5']").as("Иконка профиля пользователя");
    SelenideElement projectsDropdownButton = $x("//a[@id='browse_link']").as("Кнопка выпадающего списка проектов");
    SelenideElement testProjectLink = $x("//a[@href='/browse/TEST' and contains(text(), 'Test')]").as("Ссылка на проект TEST");

    /*
     * Метод для проверки успешной авторизации.
     * Проверяет, что иконка профиля пользователя отображается на странице, и возвращает её элемент.
     */
    @Step("Проверить успешную авторизацию пользователя и получить элемент иконки профиля")
    public SelenideElement verifyAndGetProfileIcon() {
        profileIcon.shouldBe(visible);
        return profileIcon;
    }

    // Метод для выбора проекта "TEST" из выпадающего списка "Проекты".
    @Step("Выбрать проект TEST из выпадающего списка 'Проекты'")
    public void selectTestProjectFromDropdown() {
        projectsDropdownButton.click();
        testProjectLink.shouldBe(Condition.visible);
        testProjectLink.click();
    }
}
