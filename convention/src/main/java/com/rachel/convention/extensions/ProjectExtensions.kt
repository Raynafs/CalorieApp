package extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType



internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.getVersion("kotlin-compose-compiler")
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs
        }

        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        dependencies {
            // compose
            val bom = libs.findLibrary("compose-bom").get()
            implementation(platform(bom))
            implementation(libs.getBundle("compose"))
            androidTestImplementation(platform(bom))
            debugImplementation(libs.getLibrary("compose-ui-tooling"))
            debugImplementation(libs.getLibrary("compose-ui-test-manifest"))
            androidTestImplementation(libs.getLibrary("compose-ui-test-junit4"))
            androidTestImplementation("androidx.test:runner:1.4.0")
            androidTestImplementation("androidx.test.ext:junit:1.1.3")
            // compose navigation
            implementation(libs.getLibrary("androidx-navigation-compose"))
            testImplementation(libs.getLibrary("androidx-navigation-testing"))
        }
    }
}