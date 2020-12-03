plugins {
    kotlin("jvm") version "1.4.20"
}

group = "io.github.aworley1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.20")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}
