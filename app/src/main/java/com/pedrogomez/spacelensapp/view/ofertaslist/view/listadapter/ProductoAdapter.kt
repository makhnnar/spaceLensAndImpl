package com.pedrogomez.spacelensapp.ofertaslist.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedrogomez.spacelensapp.models.PokemonData
import com.pedrogomez.spacelensapp.utils.extensions.print

class ProductoAdapter(
    private val onClickItemListener: ProductoViewHolder.OnClickItemListener
) : RecyclerView.Adapter<ProductoViewHolder>() {

    private var items: ArrayList<PokemonData> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductoViewHolder {
        val inflater = LayoutInflater.from(
            parent.context
        )
        return ProductoViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(
        holder: ProductoViewHolder,
        position: Int
    ) {
        holder.setData(
            items[position],
            onClickItemListener
        )
    }

    override fun getItemCount() = items.size

    fun setData(newItems: List<PokemonData>?) {
        newItems?.let {
            items.clear()
            items.addAll(it)
            "size in adapter ${items.size}".print()
            notifyItemInserted(items.size)
        }
    }

    fun clearData(){
        items.clear()
        notifyDataSetChanged()
    }
}