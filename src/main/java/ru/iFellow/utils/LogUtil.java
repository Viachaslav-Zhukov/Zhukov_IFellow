package ru.iFellow.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class LogUtil {

    // Метод для логирования сообщений в Allure и терминал
    @Step("Логируем: {0}")
    public static void logToAllure(String message) {
        Allure.addAttachment("Лог", message);
        System.out.println(message); // Выводим сообщение в терминал
    }
}
