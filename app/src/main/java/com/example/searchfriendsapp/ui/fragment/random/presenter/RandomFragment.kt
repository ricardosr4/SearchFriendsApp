package com.example.searchfriendsapp.ui.fragment.random.presenter

import android.animation.ValueAnimator
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        call()
        initObserver()
        navigation()
        reloadImage()
    }

    private fun initObserver() {
        randomViewModel.randomState.observe(viewLifecycleOwner) { data ->
            when (data) {
                is RandomState.Success -> {
                    loadImage(data)
                    putExtra(imageUrl = data.success.message ?: "")
                }

                is RandomState.Loading -> {
                    showLoading()
                }

                is RandomState.Error -> {
                    hideLoading()
                    Toast.makeText(context, "Error al cargar la imagen", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigation() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_randomFragment_to_homeFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.action_randomFragment_to_homeFragment)
        }
    }

    private fun animateFootprint() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            binding.imagePaw.rotationY = animatedValue
        }
        animator.duration = 1000
        animator.start()
    }

    private fun loadImage(data: RandomState.Success) {
        val imageUrl = data.success.message ?: ""
        Picasso.get().load(imageUrl).into(binding.ivDog)
    }

    private fun putExtra(imageUrl: String) {
        binding.ivDog.setOnClickListener {
            val bundle = Bundle().apply {
                putString("imageUrl", imageUrl)
            }
            findNavController().navigate(R.id.action_randomFragment_to_detailFragment2, bundle)
        }
    }

    private fun call() {
        randomViewModel.getRandomDog()
    }

    private fun reloadImage() {
        binding.imagePaw.setOnClickListener {
            randomViewModel.getRandomDog()
            animateFootprint()
        }
    }

    private fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressCircular.visibility = View.GONE
    }
}
