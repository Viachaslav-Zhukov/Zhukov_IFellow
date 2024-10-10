# UI-тестирование

Это проект по автоматизированному тестированию функциональности системы Jira. Цель проекта — протестировать ключевые функции веб-приложения Jira.  
Ссылка на тестируемый объект: [Jira](https://edujira.ifellow.ru)

## Структура проекта

Проект состоит из одного модуля и содержит как код для UI тестов, так и необходимую конфигурацию для их выполнения. Структура файлов и директорий организована следующим образом:

### Основная структура

- **src/main/java/ru/iFellow/config** — конфигурационный класс для настроек проекта.
- **src/main/java/ru/iFellow/pageObject** — классы Page Object для взаимодействия с элементами веб-страниц в тестах.

### Тестовая структура

- **src/test/java/ru/iFellow/hooks** — хуки, содержащие общие действия, выполняемые перед или после тестов (например, инициализация браузера).
- **src/test/java/ru/iFellow/tests** — тестовые сценарии, проверяющие функциональность веб-приложения.
- **src/test/resources/ru.iFellow/conf** — конфигурационный файл с настройками для тестов (например, URL и параметры для авторизации).
- **src/test/resources/ru.iFellow/drivers** — папка, содержащая `chromedriver.exe`, используемый для управления браузером Google Chrome в автоматизированных тестах.

## Chromedriver.exe

`chromedriver.exe` — это исполняемый файл, необходимый для управления браузером Google Chrome в автоматизированных тестах. Я добавил его в проект по следующим причинам:

- **Обеспечение стабильной работы тестов**: Использование локальной версии `chromedriver.exe` гарантирует, что тесты будут работать с конкретной версией драйвера, совместимой с используемой версией браузера. Это помогает избежать проблем, связанных с несовместимостью между версиями драйвера и браузера.

- **Упрощение настройки окружения**: Включение драйвера в проект позволяет избежать необходимости его установки на каждой машине разработчика или тестировщика, что упрощает процесс настройки окружения для запуска тестов. Все участники команды могут начать тестирование сразу после клонирования репозитория.

- **Автоматизация загрузки драйвера**: В зависимости от конфигурации, если `chromedriver.exe` недоступен (например, если файл был удален или не добавлен в проект), проект будет использовать **WebDriverManager** для автоматической загрузки соответствующей версии драйвера. Это обеспечивает гибкость и минимизирует проблемы с совместимостью.

- **Платформенная зависимость**: Учтите, что `chromedriver.exe` предназначен для Windows. Пользователям других операционных систем (таких как macOS или Linux) необходимо будет использовать соответствующий драйвер для их платформы. Убедитесь, что вы загружаете версию драйвера, совместимую с вашей ОС.

## Стек технологий

- **Java 17**
- **JUnit 5**
- **Selenide**
- **Maven**
- **Allure**
- **Google Chrome**

## Проверки функциональности

- **Авторизация:**
  - Проверяется успешная авторизация и отображение иконки профиля пользователя на главной странице.

- **Навигация:**
  - Проверяется успешная авторизация и переход в проект "Test" с проверкой правильности отображения аватара проекта.

- **Создание задачи:**
  - Проверяется создание новой задачи и увеличение счетчика задач на один после её создания.

- **Статус задачи:**
  - Проверяется статус задачи и версия исправления для конкретной задачи.

- **Создание и управление задачи с типом "Ошибка":**
  - Проверяется создание нового баг-репорта, его перевод через статусы "В РАБОТЕ", "РЕШЕННЫЕ" и "ГОТОВО", а также его удаление.

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

## Настройка Allure в проекте

Для подключения Allure необходимо дополнить `pom.xml` проекта.

- **В секцию properties:**

```xml
<properties>
  <maven-surefire-plugin.version>2.21.0</maven-surefire-plugin.version>
  <junit-platform-surefire-provider.version>1.1.0</junit-platform-surefire-provider.version>
  <aspectj.version>1.8.19</aspectj.version>
  <allure-junit5.version>2.6.0</allure-junit5.version>
</properties>
```
- **В секцию build:**

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.2.5</version>
      <configuration>
        <testFailureIgnore>false</testFailureIgnore>
        <argLine>
          -Dfile.encoding=${project.build.sourceEncoding}
          -javaagent:'${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar'
        </argLine>
      </configuration>
      <dependencies>
        <dependency>
          <groupId>org.aspectj</groupId>
          <artifactId>aspectjweaver</artifactId>
          <version>${aspectj.version}</version>
        </dependency>
      </dependencies>
    </plugin>
    <plugin>
      <groupId>io.qameta.allure</groupId>
      <artifactId>allure-maven</artifactId>
      <version>2.12.0</version>
      <configuration>
        <reportVersion>${allure.version}</reportVersion>
        <resultsDirectory>${project.build.directory}/allure-results</resultsDirectory>
      </configuration>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.1</version>
    </plugin>
  </plugins>
</build>
```
- **В секцию dependencies:**

```xml
<dependencies>
  <dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-junit5</artifactId>
    <version>${allure-junit5.version}</version>
  </dependency>
</dependencies>
```
- **Обратите внимание**: следите за актуальными версиями плагинов и зависимостей,
  чтобы обеспечить совместимость и использовать последние улучшения и исправления.

### Автор

- **Имя**: Вячеслав Жуков
- **Email**: [slava.slava.zhukov@yandex.by](mailto:slava.slava.zhukov@yandex.by)
- **GitHub**: [Viachaslav-Zhukov](https://github.com/Viachaslav-Zhukov)