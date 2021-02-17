package com.pedrogomez.spacelensapp.view.di

import com.pedrogomez.spacelensapp.repository.ProductsProvider
import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsRepository = module {
    single<SharedProductsViewModel.ProductsRepository> {
        ProductsProvider(
                get()
        )
    }
}

val viewModelListModule = module {
    viewModel {
        SharedProductsViewModel(
            get()
        )
    }
}