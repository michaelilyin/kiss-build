plugins {
    kotlin("jvm") version "1.3.50"
    `java-gradle-plugin`
}

apply {
    from("$rootDir/build-versions.gradle.kts")
}

gradlePlugin {
    plugins {
        create("kotlin-module") {
            id = "kotlin-module"
            implementationClass = "net.kiss.gradle.plugin.KotlinModulePlugin"
        }
        create("kotlin-spring-boot-service") {
            id = "kotlin-spring-boot-service"
            implementationClass = "net.kiss.gradle.plugin.spring.KotlinSpringBootServicePlugin"
        }
        create("kotlin-spring-boot-starter") {
            id = "kotlin-spring-boot-starter"
            implementationClass = "net.kiss.gradle.plugin.spring.KotlinSpringBootStarterPlugin"
        }
    }
}

repositories {
    jcenter()
    mavenCentral()
    gradlePluginPortal()
}

val springbootVer: String by extra
val kotlinVer: String by extra
val springbootDepPluginVer: String by extra
val buildinfoPluginVer: String by extra
val detektVer: String by extra
val ktlintVer: String by extra

dependencies {
    compileOnly(gradleApi())
    implementation(kotlin("stdlib-jdk8"))

//  project plugins

//  kotlin("jvm") version "$kotlinVer"
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVer")
//  kotlin("kapt") version "$kotlinVer"
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:$kotlinVer")
//  kotlin("plugin.spring") version "$kotlinVer"
    implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:$kotlinVer")
//  kotlin("plugin.jpa") version "$kotlinVer"
    implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:$kotlinVer")
//  id("org.springframework.boot") version "$springbootVer"
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springbootVer")
//  id("io.spring.dependency-management") version "$springboot_dep_pluginVer"
    implementation("io.spring.gradle:dependency-management-plugin:$springbootDepPluginVer")
//  id("com.pasam.gradle.buildinfo") version "$buildinfo_pluginVer"
    implementation("com.pasam.gradle.buildinfo:com.pasam.gradle.buildinfo.gradle.plugin:$buildinfoPluginVer")
//  id("io.gitlab.arturbosch.detekt") version "$detektVer"
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVer")
    //  id("org.jlleitschuh.gradle.ktlint") version "$ktlintVer"
    implementation("org.jlleitschuh.gradle:ktlint-gradle:$ktlintVer")
}
