package ru.fellow.steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import ru.fellow.config.TestConfig;
import ru.fellow.pageObject.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JiraFunctionalSteps {
    private final MainPage mainPage = new MainPage();
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();
    private final TaskPageTestSeleniumATHomework taskPageTestSeleniumATHomework = new TaskPageTestSeleniumATHomework();
    private final BugReportPage bugReportPage = new BugReportPage();
    private int initialTasksCount;

    @Дано("у пользователя открыта страница авторизации")
    public void userIsOnLoginPage() {
        System.out.println("Пользователь на странице авторизации: https://edujira.ifellow.ru.");
    }

    @Когда("пользователь вводит правильные логин и пароль")
    public void userEntersValidCredentials() {
        authorizationPage.login(TestConfig.getProperty("username"), TestConfig.getProperty("password"));
    }

    @Тогда("пользователь должен быть перенаправлен на главную страницу и видеть иконку своего профиля")
    public void userShouldSeeProfileIcon() {
        SelenideElement profileIcon = mainPage.verifyAndGetProfileIcon();
        assertTrue(profileIcon.isDisplayed(), "Иконка профиля не отображается, авторизация не удалась.");
    }

    @Тогда("пользователь выбирает проект Test из выпадающего списка проектов")
    public void userSelectsTestProject() {
        mainPage.selectTestProjectFromDropdown();
    }

    @Тогда("пользователь должен перейти на страницу проекта и видеть аватар с текстом {string}")
    public void userShouldSeeProjectAvatarWithAltText(String expectedAltText) {
        String actualAltText = taskPage.getProjectAvatarAltText();
        Assertions.assertEquals(expectedAltText, actualAltText, "Ожидался alt текст '" + expectedAltText + "' для аватара проекта.");
    }

    @Тогда("пользователь получает общее количество задач в проекте")
    public void userGetsTotalTasksCount() {
        // Получение общего количества задач
        initialTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Общее количество задач в проекте: " + initialTasksCount);
    }

    @Когда("пользователь создает новую задачу")
    public void userCreatesNewTask() {
        taskPage.createNewTask(TestConfig.getProperty("issueType"), TestConfig.getProperty("summary"));
    }

    @Тогда("количество задач должно увеличиться на 1")
    public void tasksCountShouldIncreaseByOne() {
        // Получаем обновленное количество задач.
        int updatedTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Общее количество заведенных задач после создания новой: " + updatedTasksCount);
        Assertions.assertEquals(initialTasksCount + 1, updatedTasksCount, "Количество задач не увеличилось на 1 после создания новой задачи.");
    }

    @Когда("пользователь ищет задачу {string}")
    public void userSearchesTask(String taskName) {
        taskPage.searchTask(taskName);
    }

    @Тогда("пользователь должен видеть статус задачи {string}")
    public void userShouldSeeTaskStatus(String expectedStatus) {
        String actualTaskStatus = taskPageTestSeleniumATHomework.getTaskStatus();
        Assertions.assertEquals(expectedStatus.toUpperCase(), actualTaskStatus, "Статус задачи не соответствует ожидаемому значению.");
    }

    @Тогда("пользователь должен видеть {string}")
    public void userShouldSeeFixVersion(String expectedFixVersion) {
        String actualFixVersion = taskPageTestSeleniumATHomework.getFixVersion();
        Assertions.assertEquals(expectedFixVersion, actualFixVersion, "Версия исправления не соответствует ожидаемому значению.");
    }

    @Когда("пользователь создает новый баг-репорт с описанием")
    public void userCreatesNewBug() {
        taskPage.createNewBug(TestConfig.getProperty("bugIssueType"), TestConfig.getProperty("bugSummary"), TestConfig.getProperty("bugDescription"), TestConfig.getProperty("bugEnvironment"));
    }

    @Когда("пользователь открывает созданный баг-репорт")
    public void userOpensCreatedBugReport() {
        taskPage.searchBug(TestConfig.getProperty("bugSummary"));
    }


    @Тогда("пользователь переводит баг-репорт в статус В РАБОТЕ")
    public void userTransitionsBugToInProgress() {
        SelenideElement statusElementFirst = bugReportPage.transitionBugToInProgress();
        assertTrue(statusElementFirst.isDisplayed(), "Статус не изменился на 'В РАБОТЕ'.");
    }

    @Тогда("пользователь переводит баг-репорт в статус РЕШЕННЫЕ")
    public void userTransitionsBugToResolved() {
        SelenideElement statusElementSecond = bugReportPage.transitionBugToResolved();
        assertTrue(statusElementSecond.isDisplayed(), "Статус не изменился на 'РЕШЕННЫЕ'.");
    }

    @Тогда("пользователь переводит баг-репорт в статус ГОТОВО")
    public void userTransitionsBugToDone() {
        SelenideElement actualStatusElement = bugReportPage.transitionBugToDone();
        String expectedStatus = "ГОТОВО";
        String actualStatus = actualStatusElement.getText();
        Assertions.assertEquals(expectedStatus, actualStatus, "Ожидаемый статус не совпадает с фактическим.");
    }

    @Когда("пользователь удаляет баг-репорт")
    public void userDeletesBugReport() {
        BugReportPage bugReportPage = new BugReportPage();
        bugReportPage.deleteBugReport();
    }

}
