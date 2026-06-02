plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.room.common)
    ksp(libs.androidx.room.compiler)
}
