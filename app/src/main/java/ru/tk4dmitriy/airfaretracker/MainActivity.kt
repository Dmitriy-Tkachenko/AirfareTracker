package ru.tk4dmitriy.airfaretracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import ru.tk4dmitriy.airfaretracker.databinding.ActivityMainBinding
import ru.tk4dmitriy.airfaretracker.utils.MyCustomNavHostFragment
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as MyCustomNavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNav, navController)
    }
}