package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.api(library: Any) {
    add("api", library)
}

fun DependencyHandlerScope.implementation(library: Any) {
    add("implementation", library)
}

fun DependencyHandlerScope.androidTestImplementation(library: Any) {
    add("androidTestImplementation", library)
}

fun DependencyHandlerScope.testImplementation(library: Any) {
    add("testImplementation", library)
}

fun DependencyHandlerScope.debugImplementation(library: Any) {
    add("debugImplementation", library)
}
