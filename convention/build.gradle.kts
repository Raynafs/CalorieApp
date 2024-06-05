plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

group = "com.rachel.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("hilt-convention") {
            id = "calorie.convention.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("application-convention") {
            id = "calorie.convention.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("library-convention") {
            id = "calorie.convention.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("compose-application-convention") {
            id = "calorie.convention.compose.application"
            implementationClass = "ComposeApplicationConventionPlugin"
        }
        register("compose-library-convention") {
            id = "calorie.convention.compose.library"
            implementationClass = "ComposeLibraryConventionPlugin"
        }
        register("module-convention") {
            id = "calorie.convention.module"
            implementationClass = "ModuleConventionPlugin"
        }
        register("feature-convention") {
            id = "calorie.convention.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("androidRoom") {
            id = "calorie.convention.room"
            implementationClass = "RoomConventionPlugin"
        }
    }
}