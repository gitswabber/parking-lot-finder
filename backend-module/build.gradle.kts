import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage;

repositories {
    jcenter()
}

plugins {
    id("net.ltgt.apt") version "0.21"
    id("net.ltgt.apt-idea") version "0.21"
}

apply {
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
    plugin("io.freefair.lombok")
}

apply(from = "../versions.gradle.kts")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.h2database:h2:${project.extra["h2.version"]}")

    implementation("org.mapstruct:mapstruct:${project.extra["mapstruct.version"]}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${project.extra["mapstruct.version"]}")

    implementation("com.google.guava:guava:${project.extra["guava.version"]}")
    implementation("org.apache.commons:commons-lang3:${project.extra["commons-lang3"]}")

    implementation("com.h2database:h2:${project.extra["h2.version"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

val dockerImageName: String by extra { "parking-lot-finder-backend" }
val dockerImageTag: String by extra { "0.0.1" }

tasks.named<DockerBuildImage>("dockerBuildImage") {
    inputDir.set(file("./"))
    dockerFile.set(file("Dockerfile"))
    images.set(setOf("$dockerImageName:$dockerImageTag"))
//    noCache.set(true)
}
