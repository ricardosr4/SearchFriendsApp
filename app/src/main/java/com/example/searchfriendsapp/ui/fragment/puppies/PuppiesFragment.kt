package com.example.searchfriendsapp.ui.fragment.puppies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentPuppiesBinding

class PuppiesFragment : Fragment() {
    private lateinit var binding: FragmentPuppiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPuppiesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_puppiesFragment_to_homeFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.action_puppiesFragment_to_homeFragment)
        }
    }

}