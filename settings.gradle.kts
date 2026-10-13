pluginManagement {
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

rootProject.name = "gedaudit"
// gedaudit/        — die Huelle: Fenster, Pakete (deb, Windows-Installer, dmg)
// app4webtrees/    — Git-Submodul: der geteilte Kern (GEDCOM-Leser, Regelkatalog, Pruef- und Vergleichsfenster)
//                    liegt in dessen Modul shared/, derselbe Code wie in wtWin, wtTux und wtMac.
include(":shared")
project(":shared").projectDir = file("app4webtrees/shared")
include(":gedaudit")
