package ru.iFellow.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.config.Props;
import ru.iFellow.hooks.WebHooks;
import ru.iFellow.pageObject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JiraFunctionalTests extends WebHooks {
    private static final Props props = ConfigFactory.create(Props.class);
    private final MainPage mainPage = new MainPage();
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();

    @Test
    @DisplayName("Авторизация и проверка отображения иконки профиля")
    @Description("Тест проверяет успешную авторизацию и отображение иконки профиля пользователя на главной странице")
    public void testLogin() {
        authorizationPage.login(props.username(), props.password());
        SelenideElement profileIcon = mainPage.verifyAndGetProfileIcon();
        assertTrue(profileIcon.isDisplayed(), "Профиль не отображается, авторизация не удалась.");
    }

    @Test
    @DisplayName("Авторизация и переход в проект Test")
    @Description("Тест проверяет успешную авторизацию и переход в проект 'Test' с проверкой правильности отображения аватара проекта")
    public void testLoginAndNavigateToTestProject() {
        authorizationPage.login(props.username(), props.password());
        mainPage.selectTestProjectFromDropdown();
        String actualAltText = taskPage.getProjectAvatarAltText();
        assertEquals("Test", actualAltText, "Ожидался alt текст 'Test' для аватара проекта.");
    }

    @Test
    @DisplayName("Создание новой задачи и проверка увеличения количества задач")
    @Description("Тест проверяет создание новой задачи и увеличение счетчика задач на один после ее создания")
    public void testCreateNewTaskAndVerifyCount() {
        authorizationPage.login(props.username(), props.password());
        mainPage.selectTestProjectFromDropdown();

        int initialTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Количество задач до создания новой: " + initialTasksCount);

        taskPage.createNewTask(props.issueType(), props.summary());

        int updatedTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Общее количество заведенных задач после создания новой: " + updatedTasksCount);
        assertEquals(initialTasksCount + 1, updatedTasksCount, "Количество задач не увеличилось на 1 после создания новой задачи.");
    }

    @Test
    @DisplayName("Проверка статуса и версии исправления задачи")
    @Description("Тест проверяет статус задачи и версию исправления для конкретной задачи, найденной по имени")
    public void testTaskStatusAndFixVersions() {
        authorizationPage.login(props.username(), props.password());
        mainPage.selectTestProjectFromDropdown();

        taskPage.searchTask(props.createdTaskName());
        TaskPageTestSeleniumATHomework taskPageTestSeleniumATHomework = new TaskPageTestSeleniumATHomework();

        String actualTaskTitle = taskPageTestSeleniumATHomework.getTaskTitle();
        assertEquals(props.createdTaskName(), actualTaskTitle, "Название задачи не соответствует ожидаемому значению.");

        String actualTaskStatus = taskPageTestSeleniumATHomework.getTaskStatus();
        assertEquals("СДЕЛАТЬ", actualTaskStatus, "Статус задачи не соответствует ожидаемому значению.");

        String actualFixVersion = taskPageTestSeleniumATHomework.getFixVersion();
        assertEquals("Version 2.0", actualFixVersion, "Версия исправления не соответствует ожидаемому значению.");
    }

    @Test
    @DisplayName("Создание бага и изменение его статусов")
    @Description("Тест проверяет создание нового баг-репорта, его перевод через статусы 'В РАБОТЕ', 'РЕШЕННЫЕ' и 'ГОТОВО', а затем его удаление")
    public void testCreateAndTransitionBugToClosedStatus() {
        authorizationPage.login(props.username(), props.password());
        mainPage.selectTestProjectFromDropdown();

        taskPage.createNewBug(props.bugIssueType(), props.bugSummary(), props.bugDescription(), props.bugEnvironment());
        taskPage.searchBug(props.newBugSummary());
        BugReportPage bugReportPage = new BugReportPage();

        SelenideElement statusElementFirst = bugReportPage.transitionBugToInProgress();
        assertTrue(statusElementFirst.isDisplayed(), "Статус не изменился на 'В РАБОТЕ'.");

        SelenideElement statusElementSecond = bugReportPage.transitionBugToResolved();
        assertTrue(statusElementSecond.isDisplayed(), "Статус не изменился на 'РЕШЕННЫЕ'.");

        SelenideElement actualStatusElement = bugReportPage.transitionBugToDone();
        String expectedStatus = "ГОТОВО";
        String actualStatus = actualStatusElement.getText();
        assertEquals(expectedStatus, actualStatus, "Ожидаемый статус не совпадает с фактическим.");

        bugReportPage.deleteBugReport();
    }
}
