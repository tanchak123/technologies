plugins {
    id("java")
}

tasks.register<Jar>("buildJar") {
    archiveBaseName.set(project.name)
    from(sourceSets.main.get().output)
}