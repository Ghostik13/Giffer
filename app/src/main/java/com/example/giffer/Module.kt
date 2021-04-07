package com.example.giffer

import android.app.Application
import com.example.giffer.api.GiphyApi
import com.example.giffer.repository.Repository
import com.example.giffer.util.Constants
import okhttp3.Cache
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val gifViewModelModule = module {
    viewModel { GifViewModel() }
}

val repository = module {
    fun provideRepository(api: GiphyApi): Repository {
        return Repository(api)
    }
    single { provideRepository(get()) }
}

val apiModule = module {
    fun provideGiphyApi(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }

    single { provideGiphyApi(get()) }
}

val netModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    single { provideRetrofit() }
    single { provideCache(get()) }
}