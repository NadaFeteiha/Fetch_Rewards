package com.nadafeteiha.fetchrewards.di

import com.nadafeteiha.fetchrewards.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}