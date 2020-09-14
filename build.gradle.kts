import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
}

allprojects {
    group = "io.github"
    version = "0.0.1"

    repositories {
        maven("https://maven.aliyun.com/repository/central")
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java-library-distribution")

    dependencies {
        api("org.slf4j:slf4j-api:1.7.29")
        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
        testImplementation(platform("org.junit:junit-bom:5.6.2"))
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}