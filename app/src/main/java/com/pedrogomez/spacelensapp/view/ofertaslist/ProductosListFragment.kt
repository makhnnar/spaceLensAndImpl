package com.pedrogomez.spacelensapp.ofertaslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.view.ofertadetail.ProductosDetailFragment
import com.pedrogomez.spacelensapp.ofertaslist.listadapter.ProductoViewHolder
import com.pedrogomez.spacelensapp.ofertaslist.viewmodel.ProductsListViewModel
import com.pedrogomez.spacelensapp.databinding.FragmentProductosListBinding
import com.pedrogomez.spacelensapp.utils.extensions.shortToast
import org.koin.android.viewmodel.ext.android.viewModel

class ProductosListFragment : Fragment(),
    ProductoViewHolder.OnClickItemListener{

    private val productsListViewModel : ProductsListViewModel by viewModel()

    private lateinit var binding: FragmentProductosListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentProductosListBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding.root
        initObservers()
        binding.btnToTop.hide()
        productsListViewModel.getListOfPokemons()
        return view
    }

    private fun initObservers(){
        productsListViewModel.observeApiState().observe(
            this,
            Observer {
                binding.srlContainer.isRefreshing = false
                when (it) {
                    is Result.Success -> {
                        binding.pbPokesLoading.remove()
                        binding.srlContainer.isEnabled = true
                        pageScrollListener.enablePaging(true)
                    }
                    is Result.LoadingNewContent -> {
                        binding.srlContainer.isEnabled = false
                        pageScrollListener.initFields()
                        pokemonsAdapter.clearData()
                        hideKeyboard(binding.etSearchField)
                        binding.pbPokesLoading.show()
                    }
                    is Result.LoadingMoreContent -> {
                        binding.srlContainer.isEnabled = false
                        pageScrollListener.enablePaging(false)
                        binding.pbPokesLoading.show()
                    }
                    is Result.Error -> {
                        shortToast(
                                this,
                                this.getString(R.string.search_error)
                        )
                        binding.pbPokesLoading.remove()
                        binding.srlContainer.isEnabled = true
                        pageScrollListener.enablePaging(true)
                    }
                }
            }
        )
        productsListViewModel.observeFindedApiState().observe(
            this,
            Observer {
                binding.srlContainer.isRefreshing = false
                when (it) {
                    is Result.Success -> {
                        binding.searchBtn.isClickable = true
                        binding.searchBtn.isEnabled = true
                        binding.pbPokesLoading.remove()
                    }
                    is Result.Error -> {
                        binding.searchBtn.isClickable = true
                        binding.searchBtn.isEnabled = true
                        shortToast(
                                this,
                                this.getString(R.string.search_error)
                        )
                        binding.pbPokesLoading.remove()
                    }
                    else ->{
                        binding.pbPokesLoading.show()
                        binding.searchBtn.isClickable = false
                        binding.searchBtn.isEnabled = false
                        hideKeyboard(binding.etSearchField)
                        binding.etSearchField.setText("")
                    }
                }
            }
        )
        productsListViewModel.observePokemonData().observe(
                this,
                Observer {
                    pokemonsAdapter.setData(it.toList())
                }
        )
        productsListViewModel.observeFindedPokemon().observe(
            this,
            Observer {
                if(it!=null){
                    goToBookDetail(it)
                }else{
                    shortToast(
                        this@ProductosListFragment,
                        this.getString(R.string.search_no_results)
                    )
                }
            }
        )
    }

    override fun goToBookDetail(data: PokemonData) {
        val intent = Intent(
            this,
            ProductosDetailFragment::class.java
        )
        intent.putExtra(
            ProductosDetailFragment.POKE_DATA,
            data
        )
        startActivity(
            intent
        )
    }
}