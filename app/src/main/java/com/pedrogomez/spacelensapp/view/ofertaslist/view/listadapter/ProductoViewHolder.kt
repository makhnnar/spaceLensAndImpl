package com.pedrogomez.spacelensapp.ofertaslist.listadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.models.PokemonData
import com.pedrogomez.spacelensapp.databinding.ViewHolderPokemonBinding
import com.pedrogomez.spacelensapp.models.PokeType
import com.pedrogomez.spacelensapp.utils.getDrawableResByType

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

    private var binding: ViewHolderPokemonBinding? = null

    init {
        binding = ViewHolderPokemonBinding.bind(itemView)
        context = parent.context
    }

    fun setData(
        data: PokemonData,
        onClickItemListener: OnClickItemListener
    ) {
        try{
            Glide.with(context)
                .load(
                    data.frontDefaultImg
                ).into(
                    binding?.ivPokemon!!
                )
        }catch (e:Exception){

        }
        binding?.tvName?.text = data.name
        binding?.clBgCard?.background = context.getDrawable(
            getDrawableResByType(
                    data.type?.get(0)?:PokeType.NORMAL
            )
        )
        binding?.pokemonRowContainer?.setOnClickListener {
            onClickItemListener.goToBookDetail(
                data
            )
        }
    }

    interface OnClickItemListener{
        fun goToBookDetail(data: PokemonData)
    }

}