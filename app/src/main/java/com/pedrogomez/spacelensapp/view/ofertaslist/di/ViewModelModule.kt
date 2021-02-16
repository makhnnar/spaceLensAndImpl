package com.pedrogomez.spacelensapp.ofertaslist.di

import com.pedrogomez.spacelensapp.ofertaslist.viewmodel.ProductsListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelListModule = module {
    viewModel {
        ProductsListViewModel(
            get(),
            get()
        )
    }
}