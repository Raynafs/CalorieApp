plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.data" }

dependencies {
    implementation(project(AndroidModules.Domain.remote))
    implementation(project(AndroidModules.Domain.local))

    // datastore
    implementation(libs.androidx.datastore.preference)
}
