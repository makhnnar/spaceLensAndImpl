package com.pedrogomez.spacelensapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pedrogomez.spacelensapp.databinding.ActivityMainBinding
import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedProductsViewModel : SharedProductsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}