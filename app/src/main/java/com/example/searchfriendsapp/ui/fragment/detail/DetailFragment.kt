package com.example.searchfriendsapp.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupOnClick()
        clipDetail()

    }

    private fun setupView() {
        arguments?.getString("imageUrl")?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(binding.ivDetails)
        }
    }

    private fun setupOnClick() {
        binding.btBackWhiteTermsAndConditions.isEnabled = false
        binding.btBackBlackTermsAndConditions.isEnabled = true
        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false
            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(-300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 500
                withEndAction {
                    findNavController().navigate(R.id.action_detailFragment2_to_homeFragment)
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
        }
    }
    private fun clipDetail() {
        // Usar recursos de cadenas para actualizar el texto
        binding.ivFeeding.setOnClickListener {
            binding.infoDetail.text = getString(R.string.feeding_description)
        }

        binding.ivCareful.setOnClickListener {
            binding.infoDetail.text = getString(R.string.health_description)
        }

        binding.ivAtmosphere.setOnClickListener {
            binding.infoDetail.text = getString(R.string.home_environment_description)
        }

        binding.paw.setOnClickListener {
            binding.infoDetail.text = getString(R.string.adoption_description)
        }
    }
}

