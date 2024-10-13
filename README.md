## API-тестирование
Это проект по автоматизированному тестированию API. Цель проекта — протестировать ключевые функции API, включая запросы на получение информации о персонажах сериала "Рик и Морти" и создание пользователей через API Reqres. В проекте используются фреймворки Allure для генерации отчетов и Cucumber для написания тестов в формате BDD.

Ссылки на тестируемые объекты:

- [Rick and Morty API](https://rickandmortyapi.com/)
- [Reqres API](https://reqres.in/)

## Структура проекта
Проект состоит из одного модуля и содержит как код для API тестов, так и необходимую конфигурацию для их выполнения. Структура файлов и директорий организована следующим образом:

```plaintext
src
├── main
│   └── java
│       └── ru/iFellow
│           ├── api
│           │   ├── spec
│           │   │   ├── RestAssuredClient.java
│           │   │   └── RickAndMortyRestAssuredClient.java
│           │   ├── steps
│           │   │   ├── RickAndMortyClient.java
│           │   │   └── UserClient.java
│           │   └── config
│           │       └── Props.java
│           ├── cucumber
│           │   └── steps
│           │       ├── RickAndMortySteps.java
│           │       └── UserCreationSteps.java
│           ├── model
│           │   └── CreateUser.java
│           └── utils
│               ├── AssertionUtil.java
│               ├── LogUtil.java
│               └── UserDataReader.java
└── test
    └── java
        └── ru/iFellow
            └── tests
                ├── RunRickAndMortyTests.java
                └── RunUserCreationTests.java
└── test
    └── resources
        └── ru/iFellow
            ├── features
            │   ├── rick_and_morty.feature
            │   └── user_creation.feature
            └── test_resources
                ├── conf.properties
                └── user.json
```


## Пояснения к структуре

- **src/main/java/ru/iFellow/api/spec** — содержит классы для работы с API.
- **src/main/java/ru/iFellow/api/steps** — содержит шаги для взаимодействия с API.
- **src/main/java/ru/iFellow/config** — конфигурационный интерфейс для настройки работы с API.
- **src/main/java/ru/iFellow/cucumber/steps** — классы для шагов Cucumber.
- **src/main/java/ru/iFellow/model** — класс, отвечающий за создание пользователя и другие модели.
- **src/main/java/ru/iFellow/utils** — вспомогательные утилиты, такие как классы для ассертов и работы с логами.
- **src/test/java/ru/iFellow/tests** — тесты для запуска различных сценариев.
- **src/test/resources/ru/iFellow/features** — файлы .feature, описывающие сценарии тестирования.
- **src/test/resources/ru/iFellow/test_resources** — тестовые ресурсы, такие как конфигурационные файлы и JSON с данными.

## Окружающая среда

- **Платформа:** Windows 10
- **IDE:** IntelliJ IDEA
- **Java:** 17
- **Maven:** 3.8.6

## Фреймворки / Библиотеки

- **JUnit:** 5.9.2
- **Cucumber:** 7.14.0 
- **Rest Assured:** 5.5.0 
- **Allure:** 2.24.0 


## Проверки функциональности API

- **Поиск персонажа Морти**

- **Получение последнего эпизода с Морти**

- **Получение последнего персонажа из последнего эпизода**

- **Получение данных о расе и местоположении последнего персонажа**

- **Сравнение данных Морти и последнего персонажа**

- **Создание пользователя**

- **Удаление пользователя**

- **Проверка отсутствия пользователя**

## Отчеты Allure

В проект добавлен Allure для генерации отчетов о тестировании. Отчеты предоставляют наглядную визуализацию результатов выполнения тестов, включая:

- Сводные данные о тестах (успешные, неуспешные, пропущенные).
- Временные затраты на выполнение тестов.
- Детальная информация о каждом тесте.

## Запуск тестов и генерация отчетов

- **Запуск тестов** из терминала IntelliJ IDEA:
   ```bash
   mvn clean test 

- **Генерация отчета Allure** из терминала IntelliJ IDEA:
   ```bash
  mvn allure:report

- **Для просмотра отчета Allure** в браузере используйте:
   ```bash
  mvn allure:serve

## Cucumber

В проект был добавлен фреймворк Cucumber. Cucumber позволяет:

- Описывать тестовые сценарии на естественном языке в файлах с расширением `.feature`.
- Создавать тесты, читаемые для непрограммистов.
- Разделять описание теста и код его исполнения, что улучшает понимание и поддержку тестов.

## Подключение Cucumber в проекте

Для интеграции Cucumber в проект необходимо дополнить файл `pom.xml`.

- **В секцию properties:**

```xml
<properties>
  <cucumber.version>7.2.3</cucumber.version> <!-- Укажите актуальную версию Cucumber -->
  <cucumber-junit.version>7.2.3</cucumber-junit.version> <!-- Укажите актуальную версию для интеграции с JUnit -->
</properties>
```

- **В секцию dependencies:**

```xml
<dependencies>
  <!-- Реализация Cucumber для Java -->
  <dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>${cucumber.version}</version>
  </dependency>

  <!-- Библиотека для интеграции Cucumber с JUnit -->
  <dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit-platform-engine</artifactId>
    <version>${cucumber-junit.version}</version>
  </dependency>
</dependencies>
```
- **Обратите внимание**: следите за актуальными версиями зависимостей,
  чтобы обеспечить совместимость и использовать последние улучшения и исправления.

### Автор

- **Имя**: Вячеслав Жуков
- **Email**: [slava.slava.zhukov@yandex.by](mailto:slava.slava.zhukov@yandex.by)
- **GitHub**: [Viachaslav-Zhukov](https://github.com/Viachaslav-Zhukov)



