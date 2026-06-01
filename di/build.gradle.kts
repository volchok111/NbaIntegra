plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
}
