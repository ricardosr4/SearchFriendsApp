package com.example.searchfriendsapp.ui.activity.preLogin.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.databinding.ActivityPreLoginBinding
import com.example.searchfriendsapp.ui.activity.login.presenter.LoginActivity
import com.example.searchfriendsapp.ui.activity.register.presenter.RegisterActivity

class PreLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigation()
    }

    private fun navigation() {
        binding.cvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.cvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        //esto se activa cuando tengamos la navegacion hacia home
//        binding.cvInvited.setOnClickListener {
//            val intent = Intent(this, HomeContainerActivity::class.java)
//        }
    }
}