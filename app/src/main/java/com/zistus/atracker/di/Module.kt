package com.zistus.atracker.di

import android.content.Context
import android.location.LocationManager
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.zistus.atracker.BuildConfig
import com.zistus.atracker.ui.main.auth.AuthenticationViewModel
import com.zistus.atracker.ui.main.home.HomeViewModel
import com.zistus.data.datasources.api.ApiService
import com.zistus.data.datasources.api.ApiSource
import com.zistus.data.datasources.api.ApiSourceImp
import com.zistus.data.datasources.api.firebase.FirebaseImplementation
import com.zistus.data.datasources.api.firebase.FirebaseInteractor
import com.zistus.data.datasources.db.MyDatabase
import com.zistus.data.datasources.db.room.DatabaseSource
import com.zistus.data.datasources.db.room.DatabaseSourceImpl
import com.zistus.data.datasources.db.room.dao.BaseDao
import com.zistus.data.repository.BaseRepositoryImpl
import com.zistus.data.repository.user.UserRepositoryImpl
import com.zistus.domain.repository.BaseRepository
import com.zistus.domain.repository.user.UserRepository
import com.zistus.domain.usecases.BaseUseCase
import com.zistus.domain.usecases.BaseUseCaseImp
import com.zistus.domain.usecases.user.UserUseCase
import com.zistus.domain.usecases.user.UserUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    fun provideBaseUseCase(baseRepository: BaseRepository): BaseUseCase =
        BaseUseCaseImp(baseRepository = baseRepository)

    fun provideUserUseCase(userRepository: UserRepository): UserUseCase =
        UserUseCaseImpl(userRepository = userRepository)

    single { provideBaseUseCase(get()) }
    single { provideUserUseCase(get()) }
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        AuthenticationViewModel(get())
    }
}

val apiModule = module {
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun provideApiSource(apiService: ApiService): ApiSource = ApiSourceImp(apiService = apiService)

    single { provideApiService(get()) }
    single { provideApiSource(get()) }
}

val retrofitModule = module {
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun provideCoroutineRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun provideFireBase(context: Context): FirebaseInteractor {
        return FirebaseImplementation(FirebaseAuth.getInstance(), context)
    }

    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideFireBase(androidContext()) }
//    single { provideCoroutineRetrofit(get()) }
}

val roomModule = module {
    fun provideMyDatabase(context: Context) =
        Room.databaseBuilder(context, MyDatabase::class.java, "test_db")
            .fallbackToDestructiveMigration()
            .build()

    fun provideBaseDao(myDb: MyDatabase) = myDb.baseDao()
    fun provideDatabaseSource(baseDao: BaseDao): DatabaseSource = DatabaseSourceImpl(baseDao)

    single { provideMyDatabase(androidContext()) }
    single { provideBaseDao(get()) }
    single { provideDatabaseSource(get()) }
}

val repositoryModule = module {

    fun provideBaseRepository(apiSource: ApiSource, databaseSource: DatabaseSource): BaseRepository =
        BaseRepositoryImpl(apiSource = apiSource, databaseSource = databaseSource)

    fun provideUserRepository(
        firebase: FirebaseInteractor,
        apiService: ApiService,
        databaseSource: DatabaseSource
    ): UserRepository =
        UserRepositoryImpl(firebase, apiService, databaseSource)

    single { provideBaseRepository(get(), get()) }
    single { provideUserRepository(get(), get(), get()) }
}

val fragmentModule = module {
    single { androidContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager? }
}
