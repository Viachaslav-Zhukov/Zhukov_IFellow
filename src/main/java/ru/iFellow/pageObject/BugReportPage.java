package ru.iFellow.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BugReportPage {

    SelenideElement bugStatusToMake = $x("//span[contains(text(), 'Сделать')]")
            .as("Статус бага: Сделать");
    SelenideElement businessProcessDropdown = $x("//span[@class='dropdown-text' and text()='Бизнес-процесс']")
            .as("Выпадающее меню: Бизнес-процесс");
    SelenideElement inProgressOption = $x("//span[@class='trigger-label' and text()='В процессе']")
            .as("Опция: В процессе");
    SelenideElement submitTransitionButton = $x("//input[@id='issue-workflow-transition-submit' and @value='В процессе']")
            .as("Кнопка подтверждения: В процессе");
    SelenideElement inProgressStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge') and contains(text(), 'В работе')]")
            .as("Статус бага: В работе");
    SelenideElement doneOption = $x("//span[@class='trigger-label' and text()='Исполнено']")
            .as("Опция: Исполнено");
    SelenideElement submitDoneButton = $x("//input[@id='issue-workflow-transition-submit' and @value='Исполнено']")
            .as("Кнопка подтверждения: Исполнено");
    SelenideElement resolvedStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge-done') and contains(text(), 'Решенные')]")
            .as("Статус бага: Решенные");
    SelenideElement confirmedOption = $x("//span[@class='trigger-label' and text()='Подтверждено']")
            .as("Опция: Подтверждено");
    SelenideElement confirmTransitionButton = $x("//input[@id='issue-workflow-transition-submit' and @value='Подтверждено']")
            .as("Кнопка подтверждения: Подтверждено");
    SelenideElement doneStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge') and text()='Готово']")
            .as("Статус бага: Готово");
    SelenideElement moreDropdownButton = $x("//span[@class='dropdown-text' and text()='Еще...']")
            .as("Кнопка выпадающего меню: Еще...");
    SelenideElement deleteOption = $x("//span[@class='trigger-label' and text()='Удалить']")
            .as("Опция: Удалить");
    SelenideElement deleteConfirmationButton = $x("//input[@id='delete-issue-submit' and @value='Удалить']")
            .as("Кнопка подтверждения удаления: Удалить");

    // Метод для перехода в статус "В РАБОТЕ"
    @Step("Переводим баг в статус 'В работе'")
    public SelenideElement transitionBugToInProgress() {
        bugStatusToMake.shouldBe(visible).shouldHave(text("СДЕЛАТЬ"));
        businessProcessDropdown.shouldBe(visible).click();
        inProgressOption.shouldBe(visible).click();
        submitTransitionButton.shouldBe(visible).click();
        inProgressStatus.shouldBe(visible).shouldHave(text("В РАБОТЕ"));
        return inProgressStatus.shouldBe(visible);
    }

    // Метод для перехода в статус "РЕШЕННЫЕ"
    @Step("Переводим баг в статус 'Решенные'")
    public SelenideElement transitionBugToResolved() {
        businessProcessDropdown.shouldBe(visible).click();
        doneOption.shouldBe(visible).click();
        submitDoneButton.shouldBe(visible).click();
        resolvedStatus.shouldBe(visible).shouldHave(text("РЕШЕННЫЕ"));
        return resolvedStatus.shouldBe(visible);
    }

    // Метод для перехода в статус "ГОТОВО"
    @Step("Переводим баг в статус 'Готово'")
    public SelenideElement transitionBugToDone() {
        businessProcessDropdown.shouldBe(visible).click();
        confirmedOption.shouldBe(visible).click();
        confirmTransitionButton.shouldBe(visible).click();
        doneStatus.shouldBe(visible).shouldHave(text("ГОТОВО"));
        return doneStatus.shouldBe(visible);
    }

    // Метод для удаления созданного баг-репорта
    @Step("Удаляем баг-репорт")
    public void deleteBugReport() {
        moreDropdownButton.shouldBe(visible).click();
        deleteOption.shouldBe(visible).click();
        deleteConfirmationButton.shouldBe(visible).click();
    }
}

