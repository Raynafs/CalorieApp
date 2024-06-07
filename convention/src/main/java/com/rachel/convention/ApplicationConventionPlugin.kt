import com.android.build.api.dsl.ApplicationExtension
import extensions.androidTestImplementation
import extensions.getBundle
import extensions.getLibrary
import extensions.implementation
import extensions.testImplementation
import helpers.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidSdk.targetSdk
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                // presentation
                implementation(project(AndroidModules.Ui.presentation))
                // testing
                androidTestImplementation(kotlin("test"))
                testImplementation(kotlin("test"))
                // androidx
                implementation(libs.getLibrary("androidx-core-ktx"))
                implementation(libs.getLibrary("androidx-appcompat"))
                implementation(libs.getLibrary("androidx-core-splashscreen"))
                androidTestImplementation(libs.getLibrary("androidx-test-ext-junit"))
                androidTestImplementation(libs.getLibrary("androidx-espresso-core"))
                // timber
                implementation(libs.getLibrary("timber"))
                // compose
                implementation(libs.getBundle("compose"))
            }
        }
    }
}