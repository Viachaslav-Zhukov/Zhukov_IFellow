package ru.fellow.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TheTaskPageOfTheTestProject {
    SelenideElement projectAvatar = $x("//img[@alt='Test']");
    SelenideElement taskCounter = $x("//div[@class='showing']/span");
    SelenideElement createButton = $x("//a[@id='create_link']");
    SelenideElement summaryField = $x("//input[@id='summary']");
    SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']");
    SelenideElement searchField = $x("//input[@id='quickSearchInput']");
    SelenideElement descriptionField = $x("//textarea[@id='description']");
    SelenideElement fixVersionsOption = $x("//select[@id='fixVersions']//option[contains(text(), 'Version 2.0')]");
    SelenideElement affectedVersionsOption = $x("//select[@id='versions']//option[contains(text(), 'Version 1.0')]");
    SelenideElement environmentField = $x("//textarea[@id='environment']");
    SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");
    SelenideElement closeNotificationButton = $x("//div[contains(@class, 'aui-message-success')]//button[contains(@class, 'aui-close-button')]");


    //Метод выбора "Тип задачи"
    public void clearAndEnterIssueType(String bugIssueType) {
        issueTypeField.shouldBe(visible);
        issueTypeField.click();
        issueTypeField.sendKeys(Keys.CONTROL + "a");
        issueTypeField.sendKeys(Keys.BACK_SPACE);
        issueTypeField.setValue(bugIssueType);
    }

    // Метод для получения текста аватара проекта.
    public String getProjectAvatarAltText() {
        projectAvatar.shouldBe(visible);
        return projectAvatar.getAttribute("alt");
    }

    // Метод для извлечения текущего количества задач.
    public int getTotalTasksCount() {
        String taskCounterText = taskCounter.getText();
        String[] parts = taskCounterText.split(" из ");
        return Integer.parseInt(parts[1]);
    }

    // Метод для создания новой задачи и обновления счетчика задач.
    public void createNewTask(String issueType, String summary) {
        createButton.shouldBe(visible).click();
        clearAndEnterIssueType(issueType);
        summaryField.shouldBe(visible);
        summaryField.setValue(summary);
        createIssueButton.shouldBe(visible).click();
        Selenide.sleep(3000);
        closeTaskCreationNotification();
        Selenide.refresh();
        taskCounter.shouldBe(visible);
    }

    // Метод закрытия окна уведомления.
    public void closeTaskCreationNotification() {
        if (closeNotificationButton.isDisplayed()) {
            closeNotificationButton.click();
        }
    }


    //Метод для создания нового баг-репорта
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
    public void searchTask(String taskSummary) {
        searchField.shouldBe(visible).click();
        searchField.setValue(taskSummary).pressEnter();
    }

    // Метод для поиска бага по названию.
    public void searchBug(String new_bug_summary) {
        searchField.shouldBe(visible).click();
        searchField.setValue(new_bug_summary).pressEnter();
    }

}

