package ru.iFellow.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPageTestSeleniumATHomework {

    SelenideElement taskTitle = $x("//h1[@id='summary-val']").as("Заголовок задачи");
    SelenideElement taskStatus = $x("//span[contains(text(), 'Сделать')]").as("Статус задачи");
    SelenideElement fixVersion = $x("//a[contains(text(), 'Version 2.0')]").as("Версия исправления");

    // Метод для получения текста заголовка задачи.
    @Step("Получить заголовок задачи")
    public String getTaskTitle() {
        return taskTitle.shouldBe(visible).getText();
    }

    // Метод для получения текста статуса задачи.
    @Step("Получить статус задачи")
    public String getTaskStatus() {
        return taskStatus.shouldBe(visible).getText();
    }

    // Метод для получения текста версии исправления.
    @Step("Получить версию исправления задачи")
    public String getFixVersion() {
        return fixVersion.shouldBe(visible).getText();
    }
}
