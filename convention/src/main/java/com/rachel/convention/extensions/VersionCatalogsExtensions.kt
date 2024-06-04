package extensions

import org.gradle.api.artifacts.VersionCatalog


fun VersionCatalog.getVersion(alias: String) = this.findVersion(alias).get().toString()

fun VersionCatalog.getLibrary(alias: String) = this.findLibrary(alias).get()

fun VersionCatalog.getBundle(alias: String) = this.findBundle(alias).get()