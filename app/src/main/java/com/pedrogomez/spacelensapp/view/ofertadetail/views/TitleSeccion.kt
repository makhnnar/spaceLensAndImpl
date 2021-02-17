package com.pedrogomez.spacelensapp.view.ofertadetail.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.pedrogomez.spacelensapp.R
import com.pedrogomez.spacelensapp.databinding.ViewTitleSectionBinding
import com.pedrogomez.spacelensapp.utils.extensions.getColor
import com.pedrogomez.spacelensapp.utils.extensions.getDrawable

class TitleSeccion : ConstraintLayout {

    lateinit var binding : ViewTitleSectionBinding

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        binding = ViewTitleSectionBinding.inflate(
            LayoutInflater.from(context),
            this
        )

        val a = context.obtainStyledAttributes(
            attrs, R.styleable.TitleSeccion, defStyle, 0
        )

        val titleSection = a.getString(
            R.styleable.TitleSeccion_tsTitle
        )

        val titleColor = a.getColor(
            R.styleable.TitleSeccion_tsColorTitle,
            getColor(R.color.black)
        )

        binding.tvTitleSection.text = titleSection?:""

        binding.tvTitleSection.setTextColor(titleColor)

        a.recycle()

    }

    fun setTitle(title:String){
        binding.tvTitleSection.text = title
    }

    fun setBackground(bg:Int){
        background = getDrawable(bg)
    }

}