package com.matensi.runrungo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.matensi.runrungo.databinding.ActivityMainBinding
import com.matensi.runrungo.fragments.MainFragment
import com.matensi.runrungo.fragments.SettinsFragment
import com.matensi.runrungo.fragments.TracksFragment
import com.matensi.runrungo.utils.openFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBottomNavClicks()
        openFragment(MainFragment.newInstance())
    }
    private fun onBottomNavClicks(){
        binding.bNan.setOnItemSelectedListener {
            when (it.itemId){
                R.id.id_home -> openFragment(MainFragment.newInstance())
                R.id.id_tracks ->  openFragment(TracksFragment.newInstance())
                R.id.id_settings -> openFragment(SettinsFragment())
            }
            true
        }

    }
}