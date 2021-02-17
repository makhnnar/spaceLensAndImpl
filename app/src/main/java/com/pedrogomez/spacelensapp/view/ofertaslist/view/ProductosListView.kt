package com.pedrogomez.spacelensapp.view.ofertaslist.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.ViewProductoListBinding
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.view.ofertaslist.view.listadapter.ProductoAdapter
import com.pedrogomez.spacelensapp.view.ofertaslist.view.listadapter.ProductoViewHolder
import com.pedrogomez.spacelensapp.utils.extensions.remove
import com.pedrogomez.spacelensapp.utils.extensions.show

class ProductosListView : ConstraintLayout,
    ProductoViewHolder.OnClickItemListener{

    lateinit var binding : ViewProductoListBinding

    private lateinit var productoAdapter : ProductoAdapter

    var onProductListActions : OnProductListActions? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        binding = ViewProductoListBinding.inflate(
            LayoutInflater.from(context),
            this
        )
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.ProductosListView,
            defStyle,
            0
        )
        initRecyclerView()
        a.recycle()
    }

    private fun initRecyclerView() {
        productoAdapter = ProductoAdapter(this)
        binding.rvPokeItems.apply{
            adapter = productoAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.srlContainer.setOnRefreshListener {
            onProductListActions?.loadAgain()
        }
        binding.btnToTop.setOnClickListener {
            binding.rvPokeItems.smoothScrollToPosition(0)
        }
    }

    fun hideBtnToTop(){
        binding.btnToTop.hide()
    }

    fun showLoader(){
        binding.srlContainer.isRefreshing = false
        binding.srlContainer.isEnabled = false
        binding.pbPokesLoading.show()
    }

    fun hideLoader(){
        binding.srlContainer.isRefreshing = false
        binding.srlContainer.isEnabled = true
        binding.pbPokesLoading.remove()
    }

    fun setData(productItems: List<ProductItem>){
        productoAdapter.setData(productItems)
    }

    interface OnProductListActions{
        fun loadAgain()
        fun goToItemDetail(data: ProductItem)
    }

    override fun goToItemDetail(data: ProductItem) {
        onProductListActions?.goToItemDetail(data)
    }

}