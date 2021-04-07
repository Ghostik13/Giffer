package com.example.giffer

import com.example.giffer.api.RetrofitInstance
import com.example.giffer.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val gifViewModelModule = module {
    viewModel { GifViewModel() }
}

val repository = module {
    single { Repository(get()) }
}

val retrofit = module {
    single { RetrofitInstance }
}