package com.example.searchfriendsapp.ui.activity.homeContainer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.databinding.ActivityHomeContainerBinding

class HomeContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}