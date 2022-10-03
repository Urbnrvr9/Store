Учебный проект Java Middle Developer, модуль серверы приложений

Для локального запуска проекта:

1. Переопределить [settings.xml](conf/settings.xml).
2. Добавить пользователя в конфигурации сервера и в [settings.xml](conf/settings.xml).
3. Запустить сервер.
4. ```mvn clean package```
5. ```mvn -P tomcat tomcat7:deploy```

* При использовании Apache Tomcat версия должна быть не ниже 10.

База данных:

1. Скачать H2Database
2. Запустить командой ```java -jar h2_fileName.jar```
3. Запустить скрипт [products.sql](src/main/resources/products.sql)
