package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Condition;

public class MainPage {
    SelenideElement profileIcon = $x("//img[@alt='Пользовательский профиль для AT5']");
    SelenideElement projectsDropdownButton = $x("//a[@id='browse_link']");
    SelenideElement testProjectLink = $x("//a[@href='/browse/TEST' and contains(text(), 'Test')]");

    /*
     * Метод для проверки успешной авторизации.
     * Проверяет, что иконка профиля пользователя отображается на странице, и возвращает её элемент.
     */
    public SelenideElement verifyAndGetProfileIcon() {
        profileIcon.shouldBe(visible);
        return profileIcon;
    }

    // Метод для выбора проекта "TEST" из выпадающего списка "Проекты".
    public void selectTestProjectFromDropdown() {
        projectsDropdownButton.click();
        testProjectLink.shouldBe(Condition.visible);
        testProjectLink.click();
    }
}
