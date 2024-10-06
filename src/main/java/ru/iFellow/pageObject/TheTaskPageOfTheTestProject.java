package ru.iFellow.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TheTaskPageOfTheTestProject {

    SelenideElement projectAvatar = $x("//img[@alt='Test']").as("Аватар проекта");
    SelenideElement taskCounter = $x("//div[@class='showing']/span").as("Счетчик задач");
    SelenideElement createButton = $x("//a[@id='create_link']").as("Кнопка создания задачи");
    SelenideElement summaryField = $x("//input[@id='summary']").as("Поле заголовка задачи");
    SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']").as("Кнопка создания задачи");
    SelenideElement searchField = $x("//input[@id='quickSearchInput']").as("Поле быстрого поиска");
    SelenideElement descriptionField = $x("//textarea[@id='description']").as("Поле описания задачи");
    SelenideElement fixVersionsOption = $x("//select[@id='fixVersions']//option[contains(text(), 'Version 2.0')]").as("Версия исправления");
    SelenideElement affectedVersionsOption = $x("//select[@id='versions']//option[contains(text(), 'Version 1.0')]").as("Затронутая версия");
    SelenideElement environmentField = $x("//textarea[@id='environment']").as("Поле окружения");
    SelenideElement issueTypeField = $x("//input[@id='issuetype-field']").as("Поле типа задачи");
    SelenideElement closeNotificationButton = $x("//div[contains(@class, 'aui-message-success')]//button[contains(@class, 'aui-close-button')]").as("Кнопка закрытия уведомления");

    // Метод выбора "Тип задачи"
    @Step("Выбрать тип задачи: {bugIssueType}")
    public void clearAndEnterIssueType(String bugIssueType) {
        issueTypeField.shouldBe(visible);
        issueTypeField.click();
        issueTypeField.sendKeys(Keys.CONTROL + "a");
        issueTypeField.sendKeys(Keys.BACK_SPACE);
        issueTypeField.setValue(bugIssueType);
    }

    // Метод для получения текста аватара проекта.
    @Step("Получить текст аватара проекта")
    public String getProjectAvatarAltText() {
        projectAvatar.shouldBe(visible);
        return projectAvatar.getAttribute("alt");
    }

    // Метод для извлечения текущего количества задач.
    @Step("Получить текущее количество задач")
    public int getTotalTasksCount() {
        String taskCounterText = taskCounter.getText();
        String[] parts = taskCounterText.split(" из ");
        return Integer.parseInt(parts[1]);
    }

    // Метод для создания новой задачи и обновления счетчика задач.
    @Step("Создать новую задачу с типом: {issueType} и заголовком: {summary}")
    public void createNewTask(String issueType, String summary) {
        createButton.shouldBe(visible).click();
        clearAndEnterIssueType(issueType);
        summaryField.shouldBe(visible);
        summaryField.setValue(summary);
        createIssueButton.shouldBe(visible).click();
        closeNotificationButton.shouldBe(visible).click();
        Selenide.refresh();
        taskCounter.shouldBe(visible);
    }

    // Метод для создания нового баг-репорта
    @Step("Создать новый баг-репорт с типом: {bugIssueType}, заголовком: {bugSummary}, описанием: {bugDescription} и окружением: {bugEnvironment}")
    public void createNewBug(String bugIssueType, String bugSummary, String bugDescription, String bugEnvironment) {
        createButton.shouldBe(visible).click();
        clearAndEnterIssueType(bugIssueType);
        summaryField.shouldBe(visible);
        summaryField.setValue(bugSummary);
        descriptionField.shouldBe(visible);
        descriptionField.setValue(bugDescription);
        fixVersionsOption.scrollTo().shouldBe(visible).click();
        environmentField.scrollTo().shouldBe(visible);
        environmentField.setValue(bugEnvironment);
        affectedVersionsOption.scrollTo().shouldBe(visible).click();
        createIssueButton.click();
    }

    // Метод для поиска задачи по названию.
    @Step("Поиск задачи по заголовку: {taskSummary}")
    public void searchTask(String taskSummary) {
        searchField.shouldBe(visible).click();
        searchField.setValue(taskSummary).pressEnter();
    }

    // Метод для поиска бага по названию.
    @Step("Поиск бага по заголовку: {new_bug_summary}")
    public void searchBug(String new_bug_summary) {
        searchField.shouldBe(visible).click();
        searchField.setValue(new_bug_summary).pressEnter();
    }
}
