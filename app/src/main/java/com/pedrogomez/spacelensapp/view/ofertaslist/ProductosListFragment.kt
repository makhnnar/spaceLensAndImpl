package com.pedrogomez.spacelensapp.view.ofertaslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.FragmentProductosListBinding
import com.pedrogomez.spacelensapp.view.ofertaslist.view.listadapter.ProductoViewHolder
import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.utils.extensions.shortToast
import com.pedrogomez.spacelensapp.models.result.Result
import com.pedrogomez.spacelensapp.view.ofertaslist.view.ProductosListView

class ProductosListFragment : Fragment(),
    ProductosListView.OnProductListActions{

    private val sharedProductsViewModel : SharedProductsViewModel by activityViewModels()

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
        binding.productsListView.hideBtnToTop()
        binding.productsListView.onProductListActions = this
        sharedProductsViewModel.loadContent()
        return view
    }

    private fun initObservers(){
        sharedProductsViewModel.productsListStateApi.observe(
            viewLifecycleOwner,
            Observer { result ->
                when (result) {
                    is Result.Success -> {
                        binding.productsListView.hideLoader()
                    }
                    is Result.LoadingNewContent -> {
                        binding.productsListView.showLoader()
                    }
                    is Result.LoadingMoreContent -> {
                        binding.productsListView.showLoader()
                    }
                    is Result.Error -> {
                        context?.let{
                            shortToast(
                                it,
                                this.getString(R.string.search_error)
                            )
                        }
                        binding.productsListView.hideLoader()
                    }
                }
            }
        )
        sharedProductsViewModel.productsListLiveData.observe(
                viewLifecycleOwner,
                Observer {
                    binding.productsListView.setData(it.toList())
                }
        )
    }

    override fun loadAgain() {
        sharedProductsViewModel.loadContent()
    }

    override fun goToItemDetail(data: ProductItem) {
        sharedProductsViewModel.saveFindedProduct(data)
        findNavController().navigate(R.id.action_productosListFragment_to_productosDetailFragment)
    }
}