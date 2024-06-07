plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.local" }

dependencies {
    implementation(project(":data:remote"))
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.extensions)
}
