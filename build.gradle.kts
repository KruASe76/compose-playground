import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight") version "1.5.4"
}

group = "me.kruase"
version = "1.0.0"
val release = "8"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://mvnrepository.com/artifact/com.squareup.sqldelight/runtime-jvm")
    maven("https://mvnrepository.com/artifact/com.squareup.sqldelight/sqlite-driver")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("com.squareup.sqldelight:runtime-jvm:1.5.4")
                implementation("com.squareup.sqldelight:sqlite-driver:1.5.4")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            packageVersion = version.toString()

            modules("java.sql")

            targetFormats(
                TargetFormat.AppImage,
                TargetFormat.Exe,
                TargetFormat.Rpm, TargetFormat.Deb
            )

            windows {
                packageName = "ComposePlayground"
                iconFile.set(project.file("icons/ComposePlayground.ico"))
                dirChooser = true
                shortcut = true
                menu = true
                upgradeUuid = "585551b2-095b-4cda-b548-7b7b1b95659d"
            }

            linux {
                packageName = "compose-playground"
                iconFile.set(project.file("icons/ComposePlayground.png"))
                appRelease = release
                shortcut = true
            }
        }
    }
}

sqldelight {
    database("Database") {
        packageName = "me.kruase.db"
        sourceFolders = listOf("sqldelight")
    }
}
