package com.pedrogomez.spacelensapp.view.ofertadetail.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.ViewProductoDetailBinding
import com.pedrogomez.spacelensapp.utils.extensions.print

class ProductoDetailView : ConstraintLayout {

    lateinit var binding : ViewProductoDetailBinding

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
            this,
            true
        )
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.ProductoDetailView,
                defStyle,
                0
        )

        a.recycle()
    }

    fun setData(){
        try{
            Glide.with(this)
                .load(
                    pokemonData.frontDefaultImg
                ).into(
                    binding?.ivPokemonFront!!
                )
            Glide.with(this)
                .load(
                    pokemonData.backDefaultImg
                ).into(
                    binding?.ivPokemonBack!!
                )
            pokeDetailsViewModel.getSpeciesDetails(
                pokemonData.id
            )
            binding.tvName.text = pokemonData.name
            val height = pokemonData.height/10.0f
            val weight = pokemonData.weight/10.0f
            binding.tvHeight.text = "$height m"
            binding.tvWeight.text = "$weight kg"
            binding.tvId.text = "#${pokemonData.id}"
            binding.tsfHp.setValue(pokemonData.hp?:0)
            binding.tsfAtk.setValue(pokemonData.attack?:0)
            binding.tsfDef.setValue(pokemonData.defense?:0)
            binding.tsfSpd.setValue(pokemonData.speed?:0)
            binding.tsfSpAtk.setValue(pokemonData.specialAttack?:0)
            binding.tsfSpDef.setValue(pokemonData.specialDefense?:0)
            initTypes()
            initTitleBgs()
        }catch (e: Exception){
            "bookData: error".print()
        }
        initObservers()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }


}