package com.example.searchfriendsapp.ui.fragment.random.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentRandomBinding
import com.example.searchfriendsapp.ui.fragment.random.state.RandomState
import com.example.searchfriendsapp.ui.fragment.random.viewModel.RandomViewModel
import com.squareup.picasso.Picasso

class RandomFragment : Fragment() {
    private lateinit var binding: FragmentRandomBinding
    private val randomViewModel by viewModels<RandomViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        call()
        initObserver()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_randomFragment_to_homeFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.action_randomFragment_to_homeFragment)
        }
    }

    private fun initObserver() {
        randomViewModel.randomState.observe(this) { data ->
            when (data) {
                is RandomState.Success -> {
                    Picasso.get().load(data.success.message ?: "").into(binding.ivDog)
                    reloadImage()
                }

                is RandomState.Loading -> {}

                is RandomState.Error -> {
                    Toast.makeText(context, "Error al cargar la imagen", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun call() {
        randomViewModel.getRandomDog()
    }

    private fun reloadImage() {
        binding.imagePaw.setOnClickListener {
            randomViewModel.getRandomDog()
        }
    }

}