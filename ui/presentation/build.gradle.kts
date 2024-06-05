plugins {
    id("calorie.convention.library")
    id("calorie.convention.compose.library")
    id("calorie.convention.hilt")
}

android { namespace = "com.rachel.presentation" }

dependencies {

    // design modules
    implementation(project(AndroidModules.Ui.designsystem))

    implementation(project(AndroidModules.Domain.remote))

    implementation(project(AndroidModules.Domain.local))


    implementation(project(AndroidModules.Data.data))

    implementation(libs.bundles.compose)


    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material3)


}