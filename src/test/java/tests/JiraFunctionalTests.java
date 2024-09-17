package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import config.WebHooks;
import ru.fellow.config.TaskCreationConfig;
import ru.fellow.pageObject.BugReportPage;
import ru.fellow.pageObject.MainPage;
import ru.fellow.pageObject.TaskPageTestSeleniumATHomework;
import ru.fellow.pageObject.TheTaskPageOfTheTestProject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraFunctionalTests extends WebHooks {

    @Test
    public void testLogin() {
        // Выполняем авторизацию с использованием данных из AuthConfig.
        authorizationPage.login(authConfig.getUsername(), authConfig.getPassword());

        // Инициализируем главную страницу (MainPage)
        MainPage mainPage = new MainPage();

        // Проверяем, что авторизация прошла успешно и иконка профиля отображается.
        SelenideElement profileIcon = mainPage.verifyAndGetProfileIcon();

        // Дополнительная проверка: иконка профиля отображается.
        assertTrue(profileIcon.isDisplayed(), "Профиль не отображается, авторизация не удалась.");
    }

    @Test
    public void testLoginAndNavigateToTestProject() {
        // Повторяем шаги авторизации.
        authorizationPage.login(authConfig.getUsername(), authConfig.getPassword());

        // Инициализируем главную страницу (MainPage).
        MainPage mainPage = new MainPage();

        // Переходим в проект "Test"
        mainPage.selectTestProjectFromDropdown();

        // Инициализируем страницу проекта Test.
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();

        // Проверяем, что аватар проекта отображается и содержит правильный alt текст.
        String actualAltText = taskPage.getProjectAvatarAltText();
        assertEquals("Test", actualAltText, "Ожидался alt текст 'Test' для аватара проекта.");
    }

    @Test
    public void testCreateNewTaskAndVerifyCount() {
        // Повторяем шаги авторизации.
        authorizationPage.login(authConfig.getUsername(), authConfig.getPassword());

        // Инициализируем главную страницу (MainPage).
        MainPage mainPage = new MainPage();

        // Переходим в проект "Test".
        mainPage.selectTestProjectFromDropdown();

        // Инициализируем страницу проекта Test
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();

        // Получаем количество задач до создания новой.
        int initialTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Количество задач до создания новой: " + initialTasksCount);

        // Создаем новую задачу с параметрами из конфигурации.
        taskPage.createNewTask(
                TaskCreationConfig.getIssueType(),
                TaskCreationConfig.getSummary());

        // Получаем обновленное количество задач.
        int updatedTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Общее количество заведенных задач после создания новой: " + updatedTasksCount);

        // Проверяем, что количество задач увеличилось на 1.
        assertEquals(initialTasksCount + 1, updatedTasksCount,
                "Количество задач не увеличилось на 1 после создания новой задачи.");
    }

    @Test
    public void testTaskStatusAndFixVersions() {
        // Повторяем шаги авторизации.
        authorizationPage.login(authConfig.getUsername(), authConfig.getPassword());

        // Инициализируем главную страницу (MainPage).
        MainPage mainPage = new MainPage();

        // Переходим в проект "Test".
        mainPage.selectTestProjectFromDropdown();

        // Инициализируем страницу проекта Test.
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();

        // Ищем задачу по названию.
        taskPage.searchTask(TaskCreationConfig.getCreatedTaskName());

        // Инициализируем страницу задачи.
        TaskPageTestSeleniumATHomework taskPageTestSeleniumATHomework = new TaskPageTestSeleniumATHomework();

        // Проверяем, что название задачи верное.
        String actualTaskTitle = taskPageTestSeleniumATHomework.getTaskTitle();
        assertEquals(TaskCreationConfig.getCreatedTaskName(), actualTaskTitle, "Название задачи не соответствует ожидаемому значению.");

        // Проверяем, что статус задачи "Сделать".
        String actualTaskStatus = taskPageTestSeleniumATHomework.getTaskStatus();
        assertEquals("СДЕЛАТЬ", actualTaskStatus, "Статус задачи не соответствует ожидаемому значению.");

        // Проверяем, что версия исправления "Version 2.0".
        String actualFixVersion = taskPageTestSeleniumATHomework.getFixVersion();
        assertEquals("Version 2.0", actualFixVersion, "Версия исправления не соответствует ожидаемому значению.");
    }

    @Test
    public void testCreateAndTransitionBugToClosedStatus() {
        // Повторяем шаги авторизации.
        authorizationPage.login(authConfig.getUsername(), authConfig.getPassword());

        // Инициализируем главную страницу (MainPage).
        MainPage mainPage = new MainPage();

        // Переходим в проект "Test"
        mainPage.selectTestProjectFromDropdown();

        // Инициализируем страницу проекта Test.
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();

        // Создаем новый баг-репорт с параметрами из конфигурации.
        taskPage.createNewBug(
                TaskCreationConfig.getBugIssueType(),
                TaskCreationConfig.getBugSummary(),
                TaskCreationConfig.getBugDescription(),
                TaskCreationConfig.getBugEnvironment()
        );

        // Открываем баг-репорт.
        taskPage.searchBug(TaskCreationConfig.getNewBugSummary());

        // Инициализируем страницу баг-репорта.
        BugReportPage bugReportPage = new BugReportPage();

        // Меняем статус баг-репорта на В РАБОТЕ и получаем элемент статуса.
        SelenideElement statusElementFirst = bugReportPage.transitionBugToInProgress();

        // Проверяем, что статус изменился на "В РАБОТЕ".
        assertTrue(statusElementFirst.isDisplayed());

        // Меняем статус баг-репорта на и получаем элемент статуса.
        SelenideElement statusElementSecond = bugReportPage.transitionBugToResolved();

        // Проверяем, что статус изменился на "РЕШЕННЫЕ".
        assertTrue(statusElementSecond.isDisplayed());

        // Меняем статус баг-репорта на "ГОТОВО" и получаем элемент статуса.
        SelenideElement actualStatusElement = bugReportPage.transitionBugToDone();

        // Ожидаемый статус.
        String expectedStatus = "ГОТОВО";

        // Сравниваем ожидаемый и фактический результат.
        String actualStatus = actualStatusElement.getText();
        assertEquals(expectedStatus, actualStatus, "Ожидаемый статус не совпадает с фактическим.");

        // Удаляем баг-репорт.
        bugReportPage.deleteBugReport();
    }

}


