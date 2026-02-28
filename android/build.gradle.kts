import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.compile.JavaCompile

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    val kotlinJvmTarget = if (name == "sentry_flutter") {
        JvmTarget.JVM_1_8
    } else {
        JvmTarget.JVM_17
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(kotlinJvmTarget)
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        if (name == "sentry_flutter") {
            sourceCompatibility = "1.8"
            targetCompatibility = "1.8"
        } else {
            sourceCompatibility = "17"
            targetCompatibility = "17"
        }
    }
}

val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
