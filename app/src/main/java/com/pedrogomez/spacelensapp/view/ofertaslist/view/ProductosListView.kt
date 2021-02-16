package com.pedrogomez.spacelensapp.view.ofertaslist.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.ViewProductoListBinding
import com.pedrogomez.spacelensapp.ofertaslist.listadapter.ProductoAdapter
import com.pedrogomez.spacelensapp.utils.PageScrollListener
import com.pedrogomez.spacelensapp.utils.extensions.print

class ProductosListView : ConstraintLayout {

    lateinit var binding : ViewProductoListBinding

    private lateinit var productoAdapter : ProductoAdapter

    private lateinit var pageScrollListener : PageScrollListener

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
            this,
            true
        )
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.ProductosListView,
            defStyle,
            0
        )

        initRecyclerView()
        initListeners()
        binding.btnToTop.hide()

        a.recycle()

    }

    private fun initRecyclerView() {
        productoAdapter = ProductoAdapter(this@ProductosListFragment)
        binding.rvPokeItems.apply{
            adapter = productoAdapter
            layoutManager = GridLayoutManager(this@ProductosListFragment,3)
        }
        binding.srlContainer.setOnRefreshListener {
            pokeListViewModel.getListOfPokemons()
        }
        pageScrollListener = object : PageScrollListener(
            binding.rvPokeItems.layoutManager as GridLayoutManager
        ){
            override fun onLoadMore(
                currentPage: Int
            ) {
                "current page: $currentPage".print()
                pokeListViewModel.loadMorePokemonsToList(
                    currentPage
                )
            }

            override fun scrollIsOnTop(isOnTop: Boolean) {
                if(isOnTop){
                    binding.btnToTop.hide()
                }else{
                    binding.btnToTop.show()
                }
            }
        }
        binding.rvPokeItems.addOnScrollListener(
            pageScrollListener
        )
        binding.btnToTop.setOnClickListener {
            binding.rvPokeItems.smoothScrollToPosition(0)
        }
    }

}