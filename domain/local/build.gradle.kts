plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.local" }

dependencies {
    // datastore
    implementation(libs.androidx.datastore.preference)
    implementation(libs.androidx.datastore.proto)
    implementation(project(":domain:remote"))
}