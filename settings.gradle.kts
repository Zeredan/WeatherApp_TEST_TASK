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

rootProject.name = "WeatherApp_TestTask"
include(":app")
include(":TodayForecastFeature")
include(":WeekForecastFeature")
include(":OptionsFeature")
include(":WeatherModelData")
include(":SavedOptionsModelData")
