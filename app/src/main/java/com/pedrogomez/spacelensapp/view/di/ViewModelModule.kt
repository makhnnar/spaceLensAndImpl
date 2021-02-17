package com.pedrogomez.spacelensapp.ofertaslist.di

import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelListModule = module {
    viewModel {
        SharedProductsViewModel(
            get()
        )
    }
}