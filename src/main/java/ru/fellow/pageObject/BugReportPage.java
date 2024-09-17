package ru.fellow.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class BugReportPage {
    // Локатор для статуса "СДЕЛАТЬ".
    SelenideElement bugStatusToMake = $x("//span[contains(text(), 'Сделать')]");

    // Локатор для выпадающего списка "Бизнес-процесс".
    SelenideElement businessProcessDropdown = $x("//span[@class='dropdown-text' and text()='Бизнес-процесс']");

    // Локатор для выбора варианта "В процессе" в выпадающем списке "Бизнес-процесс".
    SelenideElement inProgressOption = $x("//span[@class='trigger-label' and text()='В процессе']");

    // Локатор для подтверждения перехода в "В процессе".
    SelenideElement submitTransitionButton = $x("//input[@id='issue-workflow-transition-submit' and @value='В процессе']");

    // Локатор для статуса "В РАБОТЕ".
    SelenideElement inProgressStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge') and contains(text(), 'В работе')]");

    // Локатор для выбора варианта "Исполнено".
    SelenideElement doneOption = $x("//span[@class='trigger-label' and text()='Исполнено']");

    // Локатор для кнопки подтверждения "Исполнено".
    SelenideElement submitDoneButton = $x("//input[@id='issue-workflow-transition-submit' and @value='Исполнено']");

    // Локатор для статуса РЕШЕННЫЕ.
    SelenideElement resolvedStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge-done') and contains(text(), 'Решенные')]");

    // Локатор для выбора варианта "Подтверждено".
    SelenideElement confirmedOption = $x("//span[@class='trigger-label' and text()='Подтверждено']");

    // Локатор для кнопки "Подтверждено".
    SelenideElement confirmTransitionButton = $x("//input[@id='issue-workflow-transition-submit' and @value='Подтверждено']");

    // Локатор для статуса "Готово".
    SelenideElement doneStatus = $x("//span[contains(@class, 'jira-issue-status-lozenge') and text()='Готово']");

    // Локатор для кнопки выпадающего списка "Еще...".
    SelenideElement moreDropdownButton = $x("//span[@class='dropdown-text' and text()='Еще...']");

    // Локатор для варианта "Удалить" в выпадающем списке "Еще...".
    SelenideElement deleteOption = $x("//span[@class='trigger-label' and text()='Удалить']");

    // Локатор для кнопки "Удалить" во всплывающем окне подтверждения.
    SelenideElement deleteConfirmationButton = $x("//input[@id='delete-issue-submit' and @value='Удалить']");


    // Метод для перехода в статус "В РАБОТЕ".
    public SelenideElement transitionBugToInProgress() {
        // Проверяем, что статус задачи "СДЕЛАТЬ".
        bugStatusToMake.shouldBe(visible).shouldHave(text("СДЕЛАТЬ"));

        // Нажимаем на выпадающий список "Бизнес-процесс".
        businessProcessDropdown.shouldBe(visible).click();

        // Выбираем вариант "В процессе" из выпадающего списка.
        inProgressOption.shouldBe(visible).click();

        // Нажимаем кнопку подтверждения "В процессе".
        submitTransitionButton.shouldBe(visible).click();

        // Проверяем, что статус задачи изменился на "В РАБОТЕ".
        inProgressStatus.shouldBe(visible).shouldHave(text("В РАБОТЕ"));

        // Возвращаем элемент статуса, чтобы его можно было использовать в тесте.
        return inProgressStatus.shouldBe(visible);

    }

    // Метод для перехода в статус "РЕШЕННЫЕ".
    public SelenideElement transitionBugToResolved() {
        // Нажимаем на выпадающий список "Бизнес-процесс".
        businessProcessDropdown.shouldBe(visible).click();

        // Выбираем вариант "Исполнено" из выпадающего списка.
        doneOption.shouldBe(visible).click();

        // Нажимаем кнопку подтверждения "Исполнено".
        submitDoneButton.shouldBe(visible).click();

        // Проверяем, что статус задачи изменился на "Решенные".
        resolvedStatus.shouldBe(visible).shouldHave(text("РЕШЕННЫЕ"));

        // Возвращаем элемент статуса, чтобы его можно было использовать в тесте.
        return resolvedStatus.shouldBe(visible);
    }

    // Метод для перехода в статус "ГОТОВО".
    public SelenideElement transitionBugToDone() {
        // Нажимаем на выпадающий список "Бизнес-процесс".
        businessProcessDropdown.shouldBe(visible).click();

        // Выбираем вариант "Подтверждено" из выпадающего списка.
        confirmedOption.shouldBe(visible).click();

        // Нажимаем кнопку подтверждения "Подтверждено".
        confirmTransitionButton.shouldBe(visible).click();

        // Проверяем, что статус задачи изменился на "ГОТОВО".
        doneStatus.shouldBe(visible).shouldHave(text("ГОТОВО"));

        // Возвращаем элемент статуса, чтобы его можно было использовать в тесте.
        return doneStatus.shouldBe(visible);
    }

    // Метод для удаления созданного баг-репорта.
    public void deleteBugReport() {
        // Нажимаем на кнопку выпадающего списка "Еще...".
        moreDropdownButton.shouldBe(visible).click();

        // Выбираем вариант "Удалить" из выпадающего списка.
        deleteOption.shouldBe(visible).click();

        // Нажимаем кнопку "Удалить" во всплывающем окне подтверждения.
        deleteConfirmationButton.shouldBe(visible).click();
    }
}
