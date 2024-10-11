package ru.iFellow.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iFellow.model.CreateUser;

import java.io.File;
import java.io.IOException;

public class UserDataReader {
    private static final String USER_DATA_JSON_PATH = "src/test/resources/ru.iFellow/test_resources/user.json";

    public static CreateUser readUserDataFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(USER_DATA_JSON_PATH), CreateUser.class);
    }
}




