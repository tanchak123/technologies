plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    // Наследуем все зависимости из moduleA
    implementation(project(":moduleA"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Jar>("buildJar") {
    archiveBaseName.set(project.name)
    from(sourceSets.main.get().output)
}
