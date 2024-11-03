plugins {
    id("java")
}

group = "org.tanchak"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("buildJar") {
    archiveBaseName.set("technologies")
    from(sourceSets.main.get().output)

    subprojects.forEach { subproject ->
        dependsOn(subproject.tasks.named("buildJar"))
        from(subproject.sourceSets.main.get().output)
    }
}