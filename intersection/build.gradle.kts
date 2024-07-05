plugins {
    kotlin("jvm") version "1.9.21"
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}