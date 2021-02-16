package com.pedrogomez.spacelensapp

import android.app.Application
import com.pedrogomez.spacelensapp.view.ofertadetail.di.viewModelDetailModule
import com.pedrogomez.spacelensapp.di.networkModule
import com.pedrogomez.spacelensapp.di.pokeDataAdapterModule
import com.pedrogomez.spacelensapp.ofertaslist.di.viewModelListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpaceLensAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(
                this@SpaceLensAplication
            )
            androidLogger()
            modules(
                listOf(
                    viewModelListModule,
                    viewModelDetailModule,
                    networkModule
                )
            )
        }
    }

}