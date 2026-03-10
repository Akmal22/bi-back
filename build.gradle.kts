group = "kz.bi"
version = "0.0.1-SNAPSHOT"
description = "bi-back"

plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.session.jdbc)
    implementation(libs.spring.openapi.starter)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.liquibase.core)
    implementation(libs.postgresql)
    implementation(libs.lombok)

    annotationProcessor(libs.lombok)

    testImplementation(libs.spring.boot.test)
    testImplementation(libs.h2)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
