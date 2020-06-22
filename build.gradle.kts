plugins {
    id("org.springframework.boot") version "2.2.4.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
    id("com.bmuschko.docker-spring-boot-application") version "6.1.3" apply false
    id("io.freefair.lombok") version "4.1.6" apply false
}

allprojects {
    apply {
        plugin("java")
        plugin("idea")
        plugin("com.bmuschko.docker-spring-boot-application")
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_13
        targetCompatibility = JavaVersion.VERSION_13
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Test> {
        systemProperty("file.encoding", "UTF-8")
    }
}
