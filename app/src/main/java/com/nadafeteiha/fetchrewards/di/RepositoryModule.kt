package com.nadafeteiha.fetchrewards.di

import com.nadafeteiha.fetchrewards.data.remote.repository.Repository
import com.nadafeteiha.fetchrewards.domin.IRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::Repository) { bind<IRepository>() }
}