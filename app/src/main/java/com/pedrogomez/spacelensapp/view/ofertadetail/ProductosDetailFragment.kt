package com.pedrogomez.spacelensapp.view.ofertadetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pedrogomez.spacelensapp.models.PokemonData
import com.pedrogomez.spacelensapp.databinding.FragmentProductoDetailBinding
import com.pedrogomez.spacelensapp.view.ofertadetail.viewmodel.ProductoDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ProductosDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductoDetailBinding

    private val productoDetailsViewModel : ProductoDetailsViewModel by viewModel()

    companion object {
        const val POKE_DATA = "pokeData"
    }

    private lateinit var pokemonData : PokemonData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonData = intent.getSerializableExtra(POKE_DATA) as PokemonData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentProductoDetailBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding.root
        initObservers()
        binding.btnToTop.hide()
        pokeListViewModel.getListOfPokemons()
        return view
    }

    private fun initObservers() {
        productoDetailsViewModel.observeSpeciesDetaiData().observe(
                this,
                Observer {
                    it?.let {
                        binding.tvDescription.text = it.flavor_text_entries[0].flavor_text
                    }
                }
        )
    }

    private fun openOnBrowser(url:String){
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(browserIntent)
    }
}