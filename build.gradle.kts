//import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlin)
//    alias(libs.plugins.shadow)

    alias(libs.plugins.paper)
    alias(libs.plugins.paper.run)

    alias(libs.plugins.yml)
}

repositories {
    mavenCentral()
}

dependencies {
    library(kotlin("stdlib"))
//    library(libs.commands)
//    library(libs.config)
//    implementation(libs.metrics)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform)

    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

version = ProcessBuilder("git", "describe", "--tags", "--always", "--dirty")
    .directory(project.projectDir)
    .start()
    .inputStream
    .bufferedReader()
    .readText()
    .trim()

tasks {
    withType<AbstractArchiveTask> {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true

        from("LICENSE")

        filePermissions {
            user.read = true
            user.write = true
            user.execute = false

            group.read = true
            group.write = false
            group.execute = false

            other.read = true
            other.write = false
            other.execute = false
        }

        dirPermissions {
            user.read = true
            user.write = true
            user.execute = true

            group.read = true
            group.write = false
            group.execute = true

            other.read = false
            other.write = false
            other.execute = true
        }
    }

//    withType<ShadowJar> {
//        from("assets/text/licenses") {
//            into("licenses")
//        }
//
//        archiveClassifier = ""
//
//        enableAutoRelocation = true
//        relocationPrefix = "org.example.project.dependencies"
//
//        minimizeJar = true
//    }
//
//    jar {
//        enabled = false
//    }
}

bukkit {
    name = "Template"

    main = "org.example.project.Plugin"
    apiVersion = "1.20"
    version = project.version.toString()

    authors = listOf(
        "Esoteric Enderman"
    )

    website = "https://gitlab.com/esoterictemplates/template-minecraft-plugin"
}
