plugins {
    id("java")
}

group = "org.tanchak"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter() // Альтернативный репозиторий

}

dependencies {
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}


subprojects {
    apply(plugin = "java")

    subprojects {
        apply(plugin = "java")

        repositories {
            mavenCentral()
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }

        // Применяем зависимости только к подпроекту moduleB
        if (project.name == "moduleB" || project.name == "moduleA") {
            dependencies {
                implementation("com.alibaba:fastjson:2.0.52")
                testImplementation(platform("org.junit:junit-bom:5.10.0"))
                testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
            }
        }
    }


    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.register<Jar>("buildJar") {
    archiveBaseName.set("technologies")
    from(sourceSets.main.get().output)

    subprojects.forEach { subproject ->
        dependsOn(subproject.tasks.named("buildJar"))
        from(subproject.sourceSets.main.get().output)
    }
}