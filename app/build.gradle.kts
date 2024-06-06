@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("calorie.convention.application")
    id("calorie.convention.compose.application")
    id("calorie.convention.hilt")
}

android {
    namespace = "com.rachel.calorieapp"

    defaultConfig {
        applicationId = AndroidSdk.applicationId
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    packaging { resources { excludes += "/META-INF/*" } }
    compileSdk = 34
}

dependencies {
    implementation(project(AndroidModules.Ui.presentation))
    implementation(project(AndroidModules.Ui.designsystem))
    implementation(project(AndroidModules.Data.data))
    implementation(project(AndroidModules.Domain.local))
    implementation(project(AndroidModules.Domain.remote))
}