package com.example.searchfriendsapp.ui.fragment.home.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentHomeBinding
import com.example.searchfriendsapp.ui.fragment.home.State.HomeState
import com.example.searchfriendsapp.ui.fragment.home.adapter.AdapterHome
import com.example.searchfriendsapp.ui.fragment.home.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        call()
        homeObserver()

        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.tvRandom.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_randomFragment)
        }
        binding.tvPuppies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_puppiesFragment)
        }
        binding.tvUs.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_usFragment)
        }
    }
    private fun initRecyclerView(list: List<String>) {
        val adapter = AdapterHome(list)
        binding.recyclerHome.adapter = adapter
    }

    private fun call() {
        homeViewModel.getListDogs()
    }

    private fun homeObserver() {
        homeViewModel.homeState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeState.Success -> {
                    initRecyclerView(it.success.message ?: listOf())
                }

                is HomeState.Loading -> {

                }

                is HomeState.Error -> {

                }
            }
        }
    }
}