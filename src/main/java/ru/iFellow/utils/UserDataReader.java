package ru.iFellow.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iFellow.model.CreateUser;

import java.io.File;
import java.io.IOException;

public class UserDataReader {  // Класс для чтения данных пользователя

    // Константа с путём к файлу JSON.
    // Здесь задаётся путь к файлу user.json, который хранится в папке src/test/resources/ru.iFellow.
    // Этот путь будет использоваться для чтения файла с данными пользователя.
    private static final String USER_DATA_JSON_PATH = "src/test/resources/ru.iFellow/user.json";

    // Метод для чтения данных из JSON-файла с использованием пути по умолчанию.
    // Метод возвращает объект типа CreateUser, десериализованный из JSON-файла.
    public static CreateUser readUserDataFromJson() throws IOException {

        // Создание объекта ObjectMapper, который отвечает за преобразование JSON-данных в объекты Java.
        // Jackson ObjectMapper — это библиотека, которая умеет сериализовать Java-объекты в JSON
        // и десериализовать JSON в Java-объекты.
        ObjectMapper objectMapper = new ObjectMapper();

        // Чтение содержимого файла JSON (по указанному пути) и преобразование его в объект CreateUser.
        // Метод readValue принимает объект File и класс CreateUser, чтобы сопоставить структуру JSON
        // с полями класса CreateUser.
        return objectMapper.readValue(new File(USER_DATA_JSON_PATH), CreateUser.class);
    }
}

/*
throws IOException используется в методе readUserDataFromJson() для обработки исключений,
связанных с вводом-выводом (I/O operations).
В данном случае это исключение может возникнуть при попытке чтения файла.
Исключения — это события, которые происходят во время выполнения программы и могут быть обработаны (перехвачены и обработаны программой).
Они обычно указывают на проблемы, которые можно предсказать и с которыми можно справиться.
Примеры:
Чтение файла, которого нет на диске — это может вызвать IOException.
Деление на ноль вызовет ArithmeticException.
Попытка доступа к элементу массива за пределами его длины вызовет ArrayIndexOutOfBoundsException.
*/



