import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "me.kruase"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
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
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            packageName = "Compose Playground"
            packageVersion = "1.0.0"

            targetFormats(
                TargetFormat.AppImage,
                TargetFormat.Exe, TargetFormat.Msi,
                TargetFormat.Rpm, TargetFormat.Deb
            )

            windows {
                iconFile.set(project.file("icons/Compose Playground.ico"))
                dirChooser = true
                shortcut = true
                menu = true
                upgradeUuid = "585551b2-095b-4cda-b548-7b7b1b95659d"
            }

            linux {
                iconFile.set(project.file("icons/Compose Playground.png"))
                appRelease = "7"
                shortcut = true
            }
        }
    }
}
