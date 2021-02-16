package com.pedrogomez.spacelensapp.view.ofertadetail.di

import com.pedrogomez.spacelensapp.view.ofertadetail.viewmodel.ProductoDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelDetailModule = module {
    viewModel { ProductoDetailsViewModel(get()) }
}