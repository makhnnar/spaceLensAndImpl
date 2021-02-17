package com.pedrogomez.spacelensapp.view.ofertadetail.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.ViewProductoDetailBinding
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.utils.extensions.print

class ProductoDetailView : ConstraintLayout {

    lateinit var binding : ViewProductoDetailBinding

    var onDetailActions : OnDetailActions? = null

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
        binding = ViewProductoDetailBinding.inflate(
            LayoutInflater.from(context),
            this
        )
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.ProductoDetailView,
                defStyle,
                0
        )

        a.recycle()
    }

    fun setData(productItem: ProductItem){
        try{
            Glide.with(this)
                .load(
                    productItem.fullImag
                ).into(
                    binding.ivPokemonFront
                )
            binding.tvName.text = productItem.title
            binding.tvDate.text = productItem.created
            binding.tvPrice.text = "${productItem.price} ${productItem.currency}"
            binding.tvDescription.text = productItem.description
            binding.tvDir.text = productItem.address
            binding.tvOwner.text = productItem.owner
        }catch (e: Exception){
            "bookData: error".print()
        }
        binding.btnBack.setOnClickListener {
            onDetailActions?.onBackPressed()
        }
    }

    interface OnDetailActions{
        fun onBackPressed()
    }
}