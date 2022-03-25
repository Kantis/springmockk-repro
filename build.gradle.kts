import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Note: AWS supports only JDK 11 for Lambdas so far
val targetJdk = JavaVersion.VERSION_17

plugins {
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
}

extra["kotlin-coroutines.version"] = "1.6.0"

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.4"
    distributionType = Wrapper.DistributionType.ALL
}

dependencies {
    implementation(KotlinX.coroutines.core)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation(Testing.kotest.framework.datatest)
    testImplementation(Testing.kotest.runner.junit5)
    testImplementation(Testing.kotest.property)
    testImplementation(Testing.mockK)
    testImplementation(Testing.kotestExtensions.spring)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.ninja-squad:springmockk:_")
}

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = targetJdk.toString()
        }
    }

    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = targetJdk
            targetCompatibility = targetJdk
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
