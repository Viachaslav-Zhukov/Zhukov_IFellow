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
   mvn clean test

- **Генерация отчета Allure** из терминала IntelliJ IDEA:
   ```bash
  mvn allure:report
  
- **Для просмотра отчета Allure** в браузере используйте:
   ```bash
  mvn allure:serve

### Автор

- **Имя**: Вячеслав Жуков
- **Email**: [slava.slava.zhukov@yandex.by](mailto:slava.slava.zhukov@yandex.by)
- **GitHub**: [Viachaslav-Zhukov](https://github.com/Viachaslav-Zhukov)

