package com.example.searchfriendsapp.ui.fragment.adoptFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentAdoptBinding
import com.squareup.picasso.Picasso

class AdoptFragment : Fragment() {
    private lateinit var binding: FragmentAdoptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdoptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigation()
        loadImageDetail()

    }
    private fun loadImageDetail() {
        arguments?.getString("imageUrl")?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(binding.ivAdopt)
        }
    }
    private fun navigation(){
        binding.btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_adoptFragment_to_homeFragment)

        }
    }
}