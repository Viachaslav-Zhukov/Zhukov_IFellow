package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPageTestSeleniumATHomework {
    // Локатор для названия задачи.
    SelenideElement taskTitle = $x("//h1[@id='summary-val']");

    // Локатор для статуса задачи "Сделать".
    SelenideElement taskStatus = $x("//span[contains(text(), 'Сделать')]");

    // Локатор для версии исправления "Version 2.0".
    SelenideElement fixVersion = $x("//a[contains(text(), 'Version 2.0')]");

    // Метод для получения текста заголовка задачи.
    public String getTaskTitle() {
        return taskTitle.shouldBe(visible).getText();
    }

    // Метод для получения текста статуса задачи.
    public String getTaskStatus() {
        return taskStatus.shouldBe(visible).getText();
    }

    // Метод для получения текста версии исправления.
    public String getFixVersion() {
        return fixVersion.shouldBe(visible).getText();
    }
}
