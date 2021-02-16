package com.pedrogomez.spacelensapp.view.ofertadetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedrogomez.spacelensapp.view.ofertadetail.models.pokemonspecies.SpeciesDetails
import com.pedrogomez.spacelensapp.repository.ProductosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class ProductoDetailsViewModel(
        private val productosRepository: ProductosRepository
) : ViewModel(){

    val scope : CoroutineScope = CoroutineScope(
            Dispatchers.IO
    )

    private val speciesDetailLiveData = MutableLiveData<SpeciesDetails?>()

    fun observeSpeciesDetaiData() : MutableLiveData<SpeciesDetails?> {
        return speciesDetailLiveData
    }

    fun getSpeciesDetails(id:Int){
        scope.launch {
            speciesDetailLiveData.postValue(
                    productosRepository.getPokeDescriptionById(
                            "$id"
                    )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }

}