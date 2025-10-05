import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.tasks.JavaExec

plugins {
    kotlin("multiplatform") version "2.0.0"
    id("org.jetbrains.compose") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

kotlin {
    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val desktopTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi)
        }
    }
}

tasks.withType<JavaExec>().configureEach {
    if (name == "desktopRun") {
        logger.lifecycle("Configuring desktopRun (before) mainClass = ${mainClass.orNull}")
        mainClass.set("MainKt")
        logger.lifecycle("Configuring desktopRun (after) mainClass = ${mainClass.orNull}")
        doFirst {
            logger.lifecycle("Executing desktopRun mainClass = ${mainClass.orNull}")
        }
    }
}
