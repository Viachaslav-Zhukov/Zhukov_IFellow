package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Condition;

public class MainPage {
    // Локатор для иконки профиля пользователя.
    SelenideElement profileIcon = $x("//img[@alt='Пользовательский профиль для AT5']");

    // Локатор для кнопки выпадающего списка Проекты.
    SelenideElement projectsDropdownButton = $x("//a[@id='browse_link']");

    // Локатора для проекта "TEST".
    SelenideElement testProjectLink = $x("//a[@href='/browse/TEST' and contains(text(), 'Test')]");

    /*
     * Метод для проверки успешной авторизации.
     * Проверяет, что иконка профиля пользователя отображается на странице, и возвращает её элемент.
     */
    public SelenideElement verifyAndGetProfileIcon() {
        // Проверяем, что иконка профиля видима на странице.
        profileIcon.shouldBe(visible);

        // Возвращаем элемент иконки профиля.
        return profileIcon;
    }

    // Метод для выбора проекта "TEST" из выпадающего списка "Проекты".
    public void selectTestProjectFromDropdown() {
        // Нажимаем на кнопку выпадающего списка "Проекты".
        projectsDropdownButton.click();

        // Ожидаем, что ссылка проекта "TEST" станет доступной в выпадающем меню.
        testProjectLink.shouldBe(Condition.visible);

        // Нажимаем на ссылку проекта "TEST".
        testProjectLink.click();
    }
}
