package com.nadafeteiha.fetchrewards.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        viewModelModule,
        repositoryModule
    )
}
