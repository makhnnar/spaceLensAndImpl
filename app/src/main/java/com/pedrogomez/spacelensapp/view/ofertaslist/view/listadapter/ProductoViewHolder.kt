package com.pedrogomez.spacelensapp.view.ofertaslist.view.listadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.ViewHolderProductoBinding
import com.pedrogomez.spacelensapp.models.view.ProductItem

class ProductoViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.view_holder_producto,
        parent,
        false
    )
) {
    private var context : Context

    private var binding: ViewHolderProductoBinding? = null

    init {
        binding = ViewHolderProductoBinding.bind(itemView)
        context = parent.context
    }

    fun setData(
        data: ProductItem,
        onClickItemListener: OnClickItemListener
    ) {
        try{
            Glide.with(context)
                .load(
                    data.thumbnail
                ).into(
                    binding?.ivImgItem!!
                )
        }catch (e:Exception){

        }
        binding?.tvName?.text = data.title
        binding?.itemRowContainer?.setOnClickListener {
            onClickItemListener.goToItemDetail(
                data
            )
        }
    }

    interface OnClickItemListener{
        fun goToItemDetail(data: ProductItem)
    }

}