package com.example.kotikprob.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.kotikprob.R
import com.example.kotikprob.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        val mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.characterFragment, R.id.episodeFragment, R.id.locationFragment
        ).build()
        NavigationUI.setupWithNavController(binding.toolBar, navController, mAppBarConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }
}




