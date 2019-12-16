package net.kiss.gradle.plugin.spring

import net.kiss.gradle.plugin.KotlinModulePlugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.springframework.boot.gradle.tasks.bundling.BootJar

@Suppress("unused")
class KotlinSpringBootServicePlugin : KotlinModulePlugin() {
  override fun apply(target: Project) {
    super.apply(target)

    with(target) {
      with(plugins) {
        apply("org.jetbrains.kotlin.plugin.spring")
        apply("org.jetbrains.kotlin.plugin.jpa")
        apply("org.springframework.boot")
        apply("io.spring.dependency-management")
        apply("com.pasam.gradle.buildinfo")
      }

      tasks.create("explode", Copy::class.java) {
        it.doFirst {
          delete("$buildDir/exploded")
        }

        val jar = tasks.getByName("bootJar")

        it.from(zipTree(jar.outputs.files.singleFile))
        it.into("$buildDir/exploded")
      }

      tasks.withType(BootJar::class.java) {
        it.finalizedBy(tasks.getByName("explode"))
      }

      with(dependencies) {
        add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core")

        add("annotationProcessor", "org.springframework.boot:spring-boot-configuration-processor")
      }
    }
  }
}
