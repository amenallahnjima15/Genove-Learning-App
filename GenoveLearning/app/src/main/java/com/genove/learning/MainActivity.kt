package com.genove.learning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.genove.learning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) show(CatalogFragment())

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_catalog -> show(CatalogFragment())
                R.id.nav_enrollments -> show(PlaceholderFragment.newInstance(getString(R.string.nav_enrollments)))
                R.id.nav_savoir -> show(PlaceholderFragment.newInstance(getString(R.string.nav_savoir)))
                R.id.nav_profile -> show(ProfileFragment())
                else -> return@setOnItemSelectedListener false
            }
            true
        }
    }

    fun navigateToProfile() {
        binding.bottomNav.selectedItemId = R.id.nav_profile
    }

    private fun show(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
