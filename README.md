# UI-тестирование

Это проект по автоматизированному тестированию функциональности системы Jira. Цель проекта — протестировать ключевые функции веб-приложения Jira.  
Ссылка на тестируемый объект: [Jira](https://edujira.ifellow.ru)

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
   mvn test

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