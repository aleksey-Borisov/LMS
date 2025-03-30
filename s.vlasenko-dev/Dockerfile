# Этап 1: Сборка приложения
FROM gradle:7.5.1-jdk11 AS build

# Установите рабочую директорию
WORKDIR /app

# Копируем файлы Gradle и исходный код
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

# Выполняем сборку приложения
RUN gradle build --no-daemon

# Этап 2: Запуск приложения
FROM openjdk:11-jre-slim

# Установите рабочую директорию
WORKDIR /app

# Копируем собранный JAR файл из предыдущего этапа
COPY --from=build /app/build/libs/*.jar app.jar

# Указываем команду для запуска приложения
CMD ["java", "-jar", "app.jar"]