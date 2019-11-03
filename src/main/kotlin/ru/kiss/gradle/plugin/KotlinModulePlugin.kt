package ru.kiss.gradle.plugin

import io.gitlab.arturbosch.detekt.detekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

open class KotlinModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.jvm")
        target.plugins.apply("org.jetbrains.kotlin.kapt")
        target.plugins.apply("io.gitlab.arturbosch.detekt")
        target.plugins.apply("org.jlleitschuh.gradle.ktlint")

        target.tasks.withType(KotlinCompile::class.java) {
            it.kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }

        target.tasks.withType(Test::class.java) {
            it.useJUnitPlatform()
        }

        target.detekt {
            config = target.files("${target.rootDir}/buildSrc/config/detekt-config.yml")
            buildUponDefaultConfig = true
        }

        target.extensions.configure(KtlintExtension::class.java) {
            it.ignoreFailures.set(true)
        }

        with(target.dependencies) {
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        }
    }
}
