pluginManagement {
    includeBuild("convention")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CalorieApp"
include(":app")
include(":ui")
include(":ui:presentation")
include(":ui:designsystem")
include(":data")
include(":data:local")
include(":data:remote")
include(":domain")
