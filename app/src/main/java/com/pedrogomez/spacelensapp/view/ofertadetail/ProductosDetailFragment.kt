package com.pedrogomez.spacelensapp.view.ofertadetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.pedrogomez.spacelensapp.databinding.FragmentProductoDetailBinding
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel


class ProductosDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductoDetailBinding

    private val sharedProductsViewModel : SharedProductsViewModel by activityViewModels()

    private lateinit var productItem : ProductItem

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
}