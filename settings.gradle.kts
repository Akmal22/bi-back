rootProject.name = "bi-back"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Versions
            version("spring-boot", "3.5.6")
            version("spring-dependency-management", "1.1.7")
            version("postgresql", "42.7.5")

            // Plugins
            plugin("spring-boot", "org.springframework.boot").versionRef("spring-boot")
            plugin(
                "spring-dependency-management",
                "io.spring.dependency-management"
            ).versionRef("spring-dependency-management")

            // DEPENDENCIES
            // Spring
            library("spring-boot-starter", "org.springframework.boot", "spring-boot-starter").withoutVersion()
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
            library("spring-boot-starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").withoutVersion()
            library("spring-boot-starter-validation", "org.springframework.boot", "spring-boot-starter-validation").withoutVersion()
            library("spring-boot-starter-security", "org.springframework.boot", "spring-boot-starter-security").withoutVersion()
            library("spring-session-jdbc", "org.springframework.session", "spring-session-jdbc").withoutVersion()
            library("spring-openapi-starter", "org.springdoc", "springdoc-openapi-starter-webmvc-ui").version("2.8.14")
            library("spring-boot-starter-actuator", "org.springframework.boot", "spring-boot-starter-actuator").withoutVersion()

            // Other
            library("liquibase-core", "org.liquibase", "liquibase-core").withoutVersion()
            library("postgresql", "org.postgresql", "postgresql").versionRef("postgresql")
            library("lombok", "org.projectlombok", "lombok").withoutVersion()

            // Test
            library("junit-platform-launcher", "org.junit.platform", "junit-platform-launcher").withoutVersion()
            library("spring-boot-test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            library("h2", "com.h2database", "h2").withoutVersion()
        }
    }
}