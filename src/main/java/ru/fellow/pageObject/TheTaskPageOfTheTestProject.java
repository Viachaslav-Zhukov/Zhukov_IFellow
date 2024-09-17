package ru.fellow.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TheTaskPageOfTheTestProject {
    // Локатор для аватара проекта.
    SelenideElement projectAvatar = $x("//img[@alt='Test']");

    // Локатор для счетчика задач (например, '1 из 18530').
    SelenideElement taskCounter = $x("//div[@class='showing']/span");

    // Локатор для кнопки "Создать" по-уникальному id.
    SelenideElement createButton = $x("//a[@id='create_link']");

    // Локатор для поля "Тема" по-уникальному id.
    SelenideElement summaryField = $x("//input[@id='summary']");

    // Локатор для кнопки "Создать" во всплывающем окне.
    SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']");

    // Локатор для поля ввода поиска.
    SelenideElement searchField = $x("//input[@id='quickSearchInput']");

    // Локатор для поля "Описание".
    SelenideElement descriptionField = $x("//textarea[@id='description']");

    // Локатор для поля "Исправить в версиях" для версии Version 2.0.
    SelenideElement fixVersionsOption = $x("//select[@id='fixVersions']//option[contains(text(), 'Version 2.0')]");

    // Локатор для поля "Затронуты версии" для версии Version 1.0.
    SelenideElement affectedVersionsOption = $x("//select[@id='versions']//option[contains(text(), 'Version 1.0')]");

    // Локатор для поля "Окружение".
    SelenideElement environmentField = $x("//textarea[@id='environment']");

    // Локатор для поля выбора типа задачи.
    SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");

    // Локатор для кнопки закрытия уведомления.
    SelenideElement closeNotificationButton = $x("//div[contains(@class, 'aui-message-success')]//button[contains(@class, 'aui-close-button')]");


    //Метод выбора "Тип задачи"
    public void clearAndEnterIssueType(String bugIssueType) {
        // Поле "Тип задачи" видимо на странице.
        issueTypeField.shouldBe(visible);

        // Кликнуть в поле, чтобы активировать его.
        issueTypeField.click();

        // Использовать комбинацию клавиш для удаления текста.
        // Выделить весь текст.
        issueTypeField.sendKeys(Keys.CONTROL + "a");

        // Удалить выделенный текст.
        issueTypeField.sendKeys(Keys.BACK_SPACE);

        // Ввести новое значение в поле "Тип задачи".
        issueTypeField.setValue(bugIssueType);
    }

    // Метод для получения текста аватара проекта.
    public String getProjectAvatarAltText() {
        // Проверяем, что аватар проекта видим на странице.
        projectAvatar.shouldBe(visible);

        // Возвращаем alt текст аватара.
        return projectAvatar.getAttribute("alt");
    }

    // Метод для извлечения текущего количества задач.
    public int getTotalTasksCount() {
        // Получаем текст счетчика задач с элемента страницы, который содержит информацию о количестве задач.
        // Пример: taskCounter.getText() возвращает строку "1 из 18530", где "1" - текущая задача, а "18530" - общее количество задач.
        String taskCounterText = taskCounter.getText(); // Получаем текст, например, "1 из 18530"

        // Метод split(" из ") разбивает строку "1 из 18530" на две части.
        // В результате создается массив строк, где первая часть — это номер текущей задачи ("1"),
        // а вторая часть — это общее количество задач ("18530").
        String[] parts = taskCounterText.split(" из "); // Разделяем строку на две части: ["1", "18530"].

        // Из массива строк берем вторую часть (индекс 1), которая содержит общее количество задач.
        // Далее, преобразуем эту строку в целое число с помощью Integer.parseInt() и возвращаем это значение.
        // В данном примере это число 18530, которое является общим количеством задач в проекте.
        return Integer.parseInt(parts[1]); // Преобразуем количество задач в число (в моём случае 18530).
    }

    // Метод для создания новой задачи.
    public void createNewTask(String issueType, String summary) {
        // Открываем всплывающее окно создания задачи.
        createButton.shouldBe(visible).click();

        // Устанавливаем значение в поле "Тип задачи".
        clearAndEnterIssueType(issueType);

        // Устанавливаем значение в поле "Тема".
        summaryField.shouldBe(visible);
        summaryField.setValue(summary);

        // Кликаем по кнопке "Создать" во всплывающем окне.
        createIssueButton.shouldBe(visible).click();

        // Ожидаем и закрываем всплывающее уведомления создания задачи.
        closeTaskCreationNotification();

        // Обновляем страницу.
        Selenide.refresh();

        // Убеждаемся что счетчик задач видим после обновления.
        taskCounter.shouldBe(visible);
    }

    // Метод закрытия окна уведомления.
    public void closeTaskCreationNotification() {
        // Убедитесь, что кнопка закрытия уведомления видима.
        if (closeNotificationButton.isDisplayed()) {

            // Кликаем по кнопке закрытия.
            closeNotificationButton.click();
        }
    }


    //Метод для создания нового баг-репорта
    public void createNewBug(String bugIssueType, String bugSummary, String bugDescription, String bugEnvironment) {
        // Открываем всплывающее окно создания задачи.
        createButton.shouldBe(visible).click();

        // Устанавливаем значение в поле "Тип задачи".
        clearAndEnterIssueType(bugIssueType);

        // Устанавливаем значение в поле "Тема".
        summaryField.shouldBe(visible);
        summaryField.setValue(bugSummary);

        // Устанавливаем значение в поле "Описание".
        descriptionField.shouldBe(visible);
        descriptionField.setValue(bugDescription);

        // Прокручиваем до и выбираем "Исправить в версиях" для версии Version 2.0.
        fixVersionsOption.scrollTo().shouldBe(visible).click();

        // Прокручиваем до поля "Окружение", устанавливаем значение.
        environmentField.scrollTo().shouldBe(visible);
        environmentField.setValue(bugEnvironment);

        // Прокручиваем до и выбираем "Затронуты версии" для версии Version 1.0.
        affectedVersionsOption.scrollTo().shouldBe(visible).click();

        // Кликаем по кнопке создать.
        createIssueButton.click();
    }

    // Метод для поиска задачи по названию.
    public void searchTask(String taskSummary) {
        // Кликаем на поле поиска.
        searchField.shouldBe(visible).click();

        // Вводим текст задачи в поле поиска.
        // После ввода текста нажимаем Enter для поиска.
        searchField.setValue(taskSummary).pressEnter();
    }

    // Метод для поиска бага по названию.
    public void searchBug(String new_bug_summary) {
        // Кликаем на поле поиска.
        searchField.shouldBe(visible).click();

        // Вводим текст задачи в поле поиска
        // После ввода текста нажимаем Enter для поиска.
        searchField.setValue(new_bug_summary).pressEnter();
    }

}

