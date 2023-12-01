package com.example.ifood_app.presentation.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI
import com.example.ifood_app.R
import com.example.ifood_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inicializarNavegacao()
    }

    private fun inicializarNavegacao() {
        val navHostFragment =supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHost

        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,navController
        )
    }
}