package ru.iFellow.model;

public class CreateUser {
    // Поле для хранения имени пользователя
    private String name;

    // Поле для хранения работы пользователя
    private String job;

    // Геттер для поля name
    // Возвращает текущее имя пользователя
    public String getName() {
        return name;
    }

    // Сеттер для поля name
    // Устанавливает имя пользователя
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для поля job
    // Возвращает текущую работу пользователя
    public String getJob() {
        return job;
    }

    // Сеттер для поля job
    // Устанавливает работу пользователя
    public void setJob(String job) {
        this.job = job;
    }
}
