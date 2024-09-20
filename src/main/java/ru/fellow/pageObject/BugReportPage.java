package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class BugReportPage {
    SelenideElement bugStatusToMake = $x("//span[contains(text(), 'Сделать')]");
    SelenideElement businessProcessDropdown = $x("//span[@class='dropdown-text' and text()='Бизнес-процесс']");
    SelenideElement inProgressOption = $x("//span[@class='trigger-label' and text()='В процессе']");
    SelenideElement submitTransitionButton = $x("//input[@id='issue-workflow-transition-submit' and @value='В процессе']");
    SelenideElement inProgressStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge') and contains(text(), 'В работе')]");
    SelenideElement doneOption = $x("//span[@class='trigger-label' and text()='Исполнено']");
    SelenideElement submitDoneButton = $x("//input[@id='issue-workflow-transition-submit' and @value='Исполнено']");
    SelenideElement resolvedStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge-done') and contains(text(), 'Решенные')]");
    SelenideElement confirmedOption = $x("//span[@class='trigger-label' and text()='Подтверждено']");
    SelenideElement confirmTransitionButton = $x("//input[@id='issue-workflow-transition-submit' and @value='Подтверждено']");
    SelenideElement doneStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge') and text()='Готово']");
    SelenideElement moreDropdownButton = $x("//span[@class='dropdown-text' and text()='Еще...']");
    SelenideElement deleteOption = $x("//span[@class='trigger-label' and text()='Удалить']");
    SelenideElement deleteConfirmationButton = $x("//input[@id='delete-issue-submit' and @value='Удалить']");


    // Метод для перехода в статус "В РАБОТЕ".
    public SelenideElement transitionBugToInProgress() {
        bugStatusToMake.shouldBe(visible).shouldHave(text("СДЕЛАТЬ"));
        businessProcessDropdown.shouldBe(visible).click();
        inProgressOption.shouldBe(visible).click();
        submitTransitionButton.shouldBe(visible).click();
        inProgressStatus.shouldBe(visible).shouldHave(text("В РАБОТЕ"));
        return inProgressStatus.shouldBe(visible);
    }

    // Метод для перехода в статус "РЕШЕННЫЕ".
    public SelenideElement transitionBugToResolved() {
        businessProcessDropdown.shouldBe(visible).click();
        doneOption.shouldBe(visible).click();
        submitDoneButton.shouldBe(visible).click();
        resolvedStatus.shouldBe(visible).shouldHave(text("РЕШЕННЫЕ"));
        return resolvedStatus.shouldBe(visible);
    }

    // Метод для перехода в статус "ГОТОВО".
    public SelenideElement transitionBugToDone() {
        businessProcessDropdown.shouldBe(visible).click();
        confirmedOption.shouldBe(visible).click();
        confirmTransitionButton.shouldBe(visible).click();
        doneStatus.shouldBe(visible).shouldHave(text("ГОТОВО"));
        return doneStatus.shouldBe(visible);
    }

    // Метод для удаления созданного баг-репорта.
    public void deleteBugReport() {
        moreDropdownButton.shouldBe(visible).click();
        deleteOption.shouldBe(visible).click();
        deleteConfirmationButton.shouldBe(visible).click();
    }
}
