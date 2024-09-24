package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPageTestSeleniumATHomework {
    SelenideElement taskStatus = $x("//span[contains(text(), 'Сделать')]");
    SelenideElement fixVersion = $x("//a[contains(text(), 'Version 2.0')]");

    // Метод для получения текста статуса задачи.
    public String getTaskStatus() {
        return taskStatus.shouldBe(visible).getText();
    }

    // Метод для получения текста версии исправления.
    public String getFixVersion() {
        return fixVersion.shouldBe(visible).getText();
    }
}
