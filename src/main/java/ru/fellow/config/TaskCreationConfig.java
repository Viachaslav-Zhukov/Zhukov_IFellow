package ru.fellow.config;

public class TaskCreationConfig {
    // Фиксированные значения для создания задач.
    private static final String ISSUE_TYPE = "Задача";

    private static final String SUMMARY = "Тест";

    // Название созданной ранее задачи TestSeleniumATHomework.
    private static final String CREATED_TASK_NAME = "TestSeleniumATHomework";

    // Переменные для баг-репорта.
    private static final String BUG_ISSUE_TYPE = "Ошибка";

    private static final String BUG_SUMMARY = "Создаем тестовый баг-репорт";

    private static final String BUG_DESCRIPTION = "1.Тестовый баг";

    private static final String BUG_ENVIRONMENT = "ОС Windows 10";

    // Переменная для нового баг-репорта.
    private static final String NEW_BUG_SUMMARY = "Создаем тестовый баг-репорт";

    // Метод для получения типа задачи.
    public static String getIssueType() {
        return ISSUE_TYPE;
    }

    // Метод для получения темы задачи.
    public static String getSummary() {
        return SUMMARY;
    }

    // Метод для получения названия ранее созданной задачи TestSeleniumATHomework.
    public static String getCreatedTaskName() {
        return CREATED_TASK_NAME;
    }

    // Методы для получения значений баг-репорта.
    public static String getBugIssueType() {
        return BUG_ISSUE_TYPE;
    }

    public static String getBugSummary() {
        return BUG_SUMMARY;
    }

    public static String getBugDescription() {
        return BUG_DESCRIPTION;
    }

    public static String getBugEnvironment() {
        return BUG_ENVIRONMENT;
    }

    // Метод для получения имени нового бага-репорта.
    public static String getNewBugSummary() {
        return NEW_BUG_SUMMARY;
    }
}


