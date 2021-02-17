package com.pedrogomez.spacelensapp.view.ofertadetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.FragmentProductoDetailBinding
import com.pedrogomez.spacelensapp.view.ofertadetail.views.ProductoDetailView
import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel
import org.koin.android.viewmodel.ext.android.getViewModel


class ProductosDetailFragment : Fragment(),
    ProductoDetailView.OnDetailActions{

    private lateinit var binding: FragmentProductoDetailBinding

    private val sharedProductsViewModel by lazy {
        requireParentFragment().getViewModel<SharedProductsViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.productoDetailView.onDetailActions = this
        sharedProductsViewModel.findedProductLiveData.observe(
            viewLifecycleOwner,
            Observer {
                binding.productoDetailView.setData(
                    it
                )
            }
        )
        return view
    }

    private fun openOnBrowser(url:String){
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(browserIntent)
    }

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_productosDetailFragment_to_productosListFragment)
    }
}