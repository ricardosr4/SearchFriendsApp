package com.example.searchfriendsapp.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

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
    }

    private fun setupView() {
        arguments?.getString("imageUrl")?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(binding.ivDetails)
        }

        binding.ivBack.setOnClickListener {
            navigateBack()
        }

        binding.tvBack.setOnClickListener {
            navigateBack()
        }
    }

    private fun navigateBack() {
        findNavController().navigate(R.id.action_detailFragment2_to_randomFragment)
    }
}

