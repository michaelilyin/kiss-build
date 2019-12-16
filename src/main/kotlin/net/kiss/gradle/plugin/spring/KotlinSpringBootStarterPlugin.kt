package net.kiss.gradle.plugin.spring

import net.kiss.gradle.plugin.KotlinModulePlugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

@Suppress("unused")
class KotlinSpringBootStarterPlugin : KotlinModulePlugin() {
  override fun apply(target: Project) {
    super.apply(target)

    with(target) {
      with(plugins) {
        apply("org.jetbrains.kotlin.plugin.spring")
        apply("org.jetbrains.kotlin.plugin.jpa")
        apply("org.springframework.boot")
        apply("io.spring.dependency-management")
      }

      tasks.withType(Jar::class.java) {
        it.enabled = true
      }

      tasks.withType(BootJar::class.java) {
        it.enabled = false
      }

      with(dependencies) {
        add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core")

        add("annotationProcessor", "org.springframework.boot:spring-boot-configuration-processor")
      }
    }
  }
}
