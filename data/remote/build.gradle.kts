plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.remote" }

dependencies {
    // ktor
    implementation(libs.bundles.ktor)
    testImplementation(libs.ktor.client.mock)

    implementation(libs.kotlinx.coroutines)
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
}
