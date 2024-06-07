plugins {
    id("calorie.convention.library")
    id("calorie.convention.compose.library")
    id("calorie.convention.hilt")
}

android { namespace = "com.rachel.presentation" }

dependencies {

    // design modules
    implementation(project(AndroidModules.Ui.designsystem))

    implementation(project(AndroidModules.Data.remote))

    implementation(project(AndroidModules.Data.local))

    implementation(project(AndroidModules.Domain.domain))




    implementation(libs.bundles.compose)

    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material3)
    implementation(libs.navigation.testing)
    testImplementation("junit:junit:4.12")
}
