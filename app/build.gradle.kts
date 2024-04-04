plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.huntams.gosportapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.huntams.gosportapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":features:listproducts"))
    implementation(project(":features:profiles"))
    implementation(project(":features:shop"))

    implementation ("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt ("com.google.dagger:hilt-compiler:${Versions.hilt}")
    implementation ("androidx.paging:paging-runtime-ktx:${Versions.paging}")
    implementation ("io.coil-kt:coil:${Versions.coil}")
    implementation ("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation ("androidx.recyclerview:recyclerview:${Versions.recyclerView}")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    // reflection-free flavor
    implementation ("com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBinding}")
    implementation ("androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}")
    implementation ("androidx.navigation:navigation-ui-ktx:${Versions.navVersion}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraint}")
    testImplementation("junit:junit:${Versions.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.extJunit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
}