plugins {
    id("java")
}

group = "com.t1.studies"
version = "1.0-SNAPSHOT"

// Репозитории не нужны, так как мы берем файлы напрямую по путям через files()
repositories {}

dependencies {
    // Подключаем абсолютно все JAR-файлы из папки libs одним махом для компиляции
    testImplementation(fileTree("$projectDir/libs") { include("*.jar") })

    // Подключаем их же для движка запуска тестов
    testRuntimeOnly(fileTree("$projectDir/libs") { include("*.jar") })
}

// Принудительно заставляем Java компилировать код в UTF-8
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// Принудительно заставляем Gradle передавать UTF-8 в консоль при запуске тестов
tasks.test {
    systemProperty("file.encoding", "UTF-8")
}

configurations.all {
    resolutionStrategy {
        // Запрещаем Gradle использовать любые версии, кроме нашей локальной
        failOnVersionConflict()
    }
}

val printTestOver = tasks.register("printTestOver") {
    doLast {
        println("Test run is over")
    }
}

tasks.test {
    // Игнорирует кеш при запуске тестов
    outputs.upToDateWhen { false }
    // Включаем использование JUnit 5
    useJUnitPlatform()
    // Формирует запуск следующей задачи, после завершения первой
    finalizedBy(printTestOver)

    // Показывать детализацию тестов в консоли при сборке
    testLogging {
        events("passed", "skipped", "failed")
    }
}
