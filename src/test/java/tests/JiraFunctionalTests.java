package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import hooks.WebHooks;
import org.junit.jupiter.api.Test;
import ru.fellow.config.TestConfig;
import ru.fellow.pageObject.*;

import static org.junit.jupiter.api.Assertions.*;

public class JiraFunctionalTests extends WebHooks {
    private final MainPage mainPage = new MainPage();
    private final AuthorizationPage authorizationPage = Selenide.page(AuthorizationPage.class);

    @Test
    public void testLogin() {
        authorizationPage.login(
                TestConfig.getProperty("username"),
                TestConfig.getProperty("password")
        );
        SelenideElement profileIcon = mainPage.verifyAndGetProfileIcon();
        assertTrue(profileIcon.isDisplayed(), "Профиль не отображается, авторизация не удалась.");
    }

    @Test
    public void testLoginAndNavigateToTestProject() {
        authorizationPage.login(
                TestConfig.getProperty("username"),
                TestConfig.getProperty("password")
        );
        mainPage.selectTestProjectFromDropdown();
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();
        String actualAltText = taskPage.getProjectAvatarAltText();
        assertEquals("Test", actualAltText, "Ожидался alt текст 'Test' для аватара проекта.");
    }

    @Test
    public void testCreateNewTaskAndVerifyCount() {
        authorizationPage.login(
                TestConfig.getProperty("username"),
                TestConfig.getProperty("password")
        );
        mainPage.selectTestProjectFromDropdown();
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();
        int initialTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Количество задач до создания новой: " + initialTasksCount);
        taskPage.createNewTask(
                TestConfig.getProperty("issueType"),
                TestConfig.getProperty("summary")
        );
        int updatedTasksCount = taskPage.getTotalTasksCount();
        System.out.println("Общее количество заведенных задач после создания новой: " + updatedTasksCount);
        assertEquals(initialTasksCount + 1, updatedTasksCount,
                "Количество задач не увеличилось на 1 после создания новой задачи.");
    }

    @Test
    public void testTaskStatusAndFixVersions() {
        authorizationPage.login(
                TestConfig.getProperty("username"),
                TestConfig.getProperty("password")
        );
        mainPage.selectTestProjectFromDropdown();
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();
        taskPage.searchTask(TestConfig.getProperty("createdTaskName"));
        TaskPageTestSeleniumATHomework taskPageTestSeleniumATHomework = new TaskPageTestSeleniumATHomework();
        String actualTaskTitle = taskPageTestSeleniumATHomework.getTaskTitle();
        assertEquals(TestConfig.getProperty("createdTaskName"), actualTaskTitle, "Название задачи не соответствует ожидаемому значению.");
        String actualTaskStatus = taskPageTestSeleniumATHomework.getTaskStatus();
        assertEquals("СДЕЛАТЬ", actualTaskStatus, "Статус задачи не соответствует ожидаемому значению.");
        String actualFixVersion = taskPageTestSeleniumATHomework.getFixVersion();
        assertEquals("Version 2.0", actualFixVersion, "Версия исправления не соответствует ожидаемому значению.");
    }

    @Test
    public void testCreateAndTransitionBugToClosedStatus() {
        authorizationPage.login(
                TestConfig.getProperty("username"),
                TestConfig.getProperty("password")
        );
        mainPage.selectTestProjectFromDropdown();
        TheTaskPageOfTheTestProject taskPage = new TheTaskPageOfTheTestProject();
        taskPage.createNewBug(
                TestConfig.getProperty("bugIssueType"),
                TestConfig.getProperty("bugSummary"),
                TestConfig.getProperty("bugDescription"),
                TestConfig.getProperty("bugEnvironment")
        );
        taskPage.searchBug(TestConfig.getProperty("newBugSummary"));
        BugReportPage bugReportPage = new BugReportPage();
        SelenideElement statusElementFirst = bugReportPage.transitionBugToInProgress();
        assertTrue(statusElementFirst.isDisplayed());
        SelenideElement statusElementSecond = bugReportPage.transitionBugToResolved();
        assertTrue(statusElementSecond.isDisplayed());
        SelenideElement actualStatusElement = bugReportPage.transitionBugToDone();
        String expectedStatus = "ГОТОВО";
        String actualStatus = actualStatusElement.getText();
        assertEquals(expectedStatus, actualStatus, "Ожидаемый статус не совпадает с фактическим.");
        bugReportPage.deleteBugReport();
    }
}
