plugins { id("calorie.convention.module") }

android { namespace = "com.rachel.remote" }

dependencies {

    // ktor
    implementation(libs.bundles.ktor)
    testImplementation(libs.ktor.client.mock)

    // datastore
    implementation(libs.androidx.datastore.preference)
    implementation(libs.androidx.datastore.proto)

    implementation(libs.kotlinx.coroutines)
}