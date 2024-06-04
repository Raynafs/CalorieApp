plugins {
    id("calorie.convention.library")
    id("calorie.convention.hilt")
    id("calorie.convention.compose.library")
}

android { namespace = "com.rachel.presentation" }

dependencies {

    // design modules
    implementation(project(AndroidModules.Ui.designsystem))

    implementation(project(AndroidModules.Domain.remote))

    implementation(project(AndroidModules.Domain.local))

    //
    implementation(project(AndroidModules.Data.data))

    implementation(libs.bundles.compose)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material3)

    implementation(libs.google.play.services)


}