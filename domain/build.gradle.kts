plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.data" }

dependencies {
    implementation(project(AndroidModules.Data.remote))

    implementation(project(AndroidModules.Data.local))

}