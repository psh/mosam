1. Clone the repo
2. Update AGP and Gradle to latest
3. Update to Kotlin 1.9.10 
4. Move the API key to ~/.gradle/gradle.properties and use the "by project" accessor
    * Why  ~/.gradle/gradle.properties?  It is outside the project, had neater syntax for access and Gradle have rejected the idea of "local.properties" (despite it being used in Android projects) in favor of clear semantics over the different uses of "gradle.properties" instead.