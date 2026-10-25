plugins {
    id("java")
}

group = "com.t1.studies"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // Откуда Gradle будет скачивать библиотеки
}

dependencies {
    // Платформа для запуска тестов JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    // Включаем использование JUnit 5
    useJUnitPlatform()

    // Показывать детализацию тестов в консоли при сборке
    testLogging {
        events("passed", "skipped", "failed")
    }
}