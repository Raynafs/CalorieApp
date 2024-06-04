plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.domain" }

dependencies {
    implementation(project(AndroidModules.Domain.remote))
    implementation(project(AndroidModules.Domain.local))
}