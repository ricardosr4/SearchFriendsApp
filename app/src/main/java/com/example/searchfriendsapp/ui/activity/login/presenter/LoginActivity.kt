package com.example.searchfriendsapp.ui.activity.login.presenter

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.databinding.ActivityLoginBinding
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigation()
    }

    private fun navigation() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, PreLoginActivity::class.java)
            startActivity(intent)
        }
    }
}