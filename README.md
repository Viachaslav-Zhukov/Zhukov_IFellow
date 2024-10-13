## API-тестирование
Это проект по автоматизированному тестированию API. Цель проекта — протестировать ключевые функции API, включая запросы на получение информации о персонажах сериала "Рик и Морти" и создание пользователей через API Reqres. В проекте используются фреймворки Allure для генерации отчетов и Cucumber для написания тестов в формате BDD.

Ссылки на тестируемые объекты:

- [Rick and Morty API](https://rickandmortyapi.com/)
- [Reqres API](https://reqres.in/)

## Структура проекта
Проект состоит из одного модуля и содержит как код для API тестов, так и необходимую конфигурацию для их выполнения. Структура файлов и директорий организована следующим образом:

### Основная структура

- **src/main/java/ru/iFellow/api/spec** — классы для взаимодействия с API.

- **src/main/java/ru/iFellow/api/steps** — классы с методами для API.

- **src/main/java/ru/iFellow/config** — конфигурационный интерфейс с настройками для работы с API.

- **src/main/java/ru/iFellow/cucumber/steps** — классы со стэпами для Cucumber.

- **src/main/java/ru/iFellow/model** — класс для создания пользователя, содержащий необходимые поля.

- **src/main/java/ru/iFellow/utils** — вспомогательные классы (например, класс для чтения данных пользователя из JSON файла).

### Тестовая структура

- **src/test/java/ru/iFellow/tests** — классы для запуска тестов Cucumber, разделенные по задачам (например, тесты для сериала "Рик и Морти" и тесты создания пользователей).

- **src/test/resources/ru.iFellow/features** — файлы `.feature` для описания сценариев тестирования с использованием Cucumber.

- **src/test/resources/ru.iFellow/test_resources** — тестовые ресурсы, включая файлы конфигурации и данные (например, `conf.properties` и `user.json`).

## Стек технологий

- **Java 17**
- **JUnit 5**
- **Selenide**
- **Maven**
- **Allure**
- **Cucumber**
- **Rest Assured**

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



