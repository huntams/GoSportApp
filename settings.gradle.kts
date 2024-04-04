pluginManagement {
    repositories {
        google()
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

rootProject.name = "GoSportApp"
include(":app")
include(":core:domain")
include(":core:network")
include(":core:model")
include(":core:data")
include(":features:listproducts")
include(":features:profiles")
include(":features:shop")
