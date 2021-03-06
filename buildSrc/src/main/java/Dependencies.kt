object ApplicationId {
    val application_id = "com.zistus.atracker"
}

object Releases {
    val version_code = 1
    val version_name = " alpha 1.0.0"
}

object Modules {
    val domain = ":domain"
    val common = ":common"
    val data = ":data"
}

object SDKVersions {
    val min_sdk = 21
    val target_sdk = 28
    var compile_sdk = 28
}


object Versions {
    val kotlin = "1.3.30"
    val gradle = "3.2.1"
    val realm = "5.3.0"
    val retrofit = "2.4.0"
    val retrofit_logger = "3.10.0"
    val android_arch = "1.1.1"
    val lifecycle_version = "2.1.0"
    val navigation_architecture = "2.0.0"
    val room_version = "2.1.0-alpha01"
    val fragment_version = "1.2.0"

    val koin_version = "2.0.1"
    val nav_verison = "2.1.0"

    val support = "1.1.0"
    val lagacy = "1.0.0"
    val core = "1.1.0"
    val design = "1.0.0-rc01"
    val constrainst_layout = "1.1.3"
    val appcompat = "1.1.0"

    val timber_version = "4.7.1"
    val easy_pref_version = "1.9.0"

    val lottie = "2.5.1"
    val glide = "4.6.1"
    val rx_android = "2.1.1"
    val rx_java = "2.2.15"
    val rx_kotlin = "2.3.0"

    val coroutine_version = "1.3.0"
    val calligraphy = "2.3.0"
    val pager_indicator = "1.0.3"
    val biometric_version = "1.0.0"
    val espresso = "3.1.0"
    val espresso_contrib = "3.1.0"
    val junit = "4.12"
    val junit_x = "1.1.0"
    val mockito = "2.13.0"
    val mockito_core = "2.28.2"
    val support_test = "1.0.2"
    val core_test = "1.0.0"
    val robo_electric = "1.0.0"
    val fragment_testing = "1.1.0"

    val firebase_auth_version = "19.2.0"
    val firebase_db_version = "19.2.0"
    val firebase_firestore_version = "21.3.1"
    val firebase_location_version = "17.0.0"
    val firebase_analytics_version = "17.2.1"
    val anko_version = "0.10.8"
}

object ClassPaths {
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin_ext = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    val gradle_build = "com.android.tools.build:gradle:${Versions.gradle}"
    val realm = "io.realm:realm-gradle-plugin:${Versions.realm}"
}

object Deps {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val kotlin_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofix_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val retrofit_http_logger = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_logger}"

    val room = "androidx.room:room-runtime:${Versions.room_version}"
    val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
    val room_rxjava = "androidx.room:room-rxjava2:${Versions.room_version}"
    val lifecycle_runtime = "android.arch.lifecycle:runtime:${Versions.lifecycle_version}"
    val lifecycle_ext = "android.arch.lifecycle:extensions:${Versions.lifecycle_version}"
    val lifecycle_compiler = "android.arch.lifecycle:compiler:${Versions.lifecycle_version}"

    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    var glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rx_java}"
    val rx_bind = "com.jakewharton.rxbinding:rxbinding:0.4.0"
    val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rx_kotlin}"
    val rx_compiler = "android.arch.persistence.room:compiler:${Versions.rx_java}"

    var coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_version}"
    var coroutine_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_version}"


    val calligraphy = "uk.co.chrisjenx:calligraphy:${Versions.calligraphy}"
    val pager_indicator = "com.romandanylyk:pageindicatorview:${Versions.pager_indicator}"
    val biometric = "androidx.biometric:biometric:${Versions.biometric_version}"

    val koin = "org.koin:koin-android:${Versions.koin_version}"
    val koin_scope = "org.koin:koin-android-scope:${Versions.koin_version}"
    val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koin_version}"

    val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_verison}"
    val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav_verison}"

    val anko_commons = "org.jetbrains.anko:anko-commons:${Versions.anko_version}"
    val anko_designs = "org.jetbrains.anko:anko-design:${Versions.anko_version}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"

    val easyPref = "com.pixplicity.easyprefs:library:${Versions.easy_pref_version}"

    val firebase_auth = "com.google.firebase:firebase-auth:${Versions.firebase_auth_version}"
    val google_location = "com.google.android.gms:play-services-location:${Versions.firebase_location_version}"
    val firebase_db = "com.google.firebase:firebase-database:${Versions.firebase_db_version}"
    val firebase_analytics = "com.google.firebase:firebase-analytics:${Versions.firebase_analytics_version}"
}


object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    val supportLagacy = "androidx.legacy:legacy-support-v4:${Versions.lagacy}"
    val core = "androidx.core:core-ktx:${Versions.core}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.support}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.support}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constrainst_layout}"
}

object TestDeps {
    val junit = "junit:junit:${Versions.junit}"
    val junit_x = "androidx.test.ext:junit:${Versions.junit_x}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val espresso_intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_contrib}"
    val mocito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    val arch_testing = "android.arch.core:core-testing:${Versions.android_arch}"
    val support_test = "androidx.test:runner:${Versions.support_test}"
    val core_test = "androidx.test:core:${Versions.core_test}"
    val fragment_test = "androidx.fragment:fragment-testing:${Versions.fragment_testing}"
    val test_rule = "androidx.test:rules:1.1.0"
}