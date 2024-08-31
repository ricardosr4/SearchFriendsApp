package com.example.searchfriendsapp.ui.fragment.us

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentUsBinding

class UsFragment : Fragment() {
    lateinit var binding: FragmentUsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_usFragment_to_homeFragment)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_usFragment_to_homeFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.action_usFragment_to_homeFragment)
        }
    }
}