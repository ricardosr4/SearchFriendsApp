package com.example.searchfriendsapp.ui.activity.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.ActivitySplashBinding
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //Aplica la animación al ImageView
        val zoomIn = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        binding.searchFriendLogo.startAnimation(zoomIn)


        //Se congigura el tiempo de espera antes de iniciar la MainActivity
        val splashTimeOut: Long = 3000 //3 segundos de duración
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, PreLoginActivity::class.java)
            startActivity(mainIntent)
            finish()//Cierra la SplashActivity
        }, splashTimeOut)


    }
}