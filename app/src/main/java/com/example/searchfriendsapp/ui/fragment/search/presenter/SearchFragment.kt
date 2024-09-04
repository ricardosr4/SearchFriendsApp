package com.example.searchfriendsapp.ui.fragment.search.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentSearchBinding
import com.example.searchfriendsapp.ui.fragment.search.state.SearchState
import com.example.searchfriendsapp.ui.fragment.search.viewModel.SearchViewModel
import com.squareup.picasso.Picasso


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchView()
        searchObserver()


        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }
    }


    private fun searchObserver() {
        searchViewModel.searchState.observe(viewLifecycleOwner) {data->
            when (data) {
                is SearchState.Success -> {
                    val imageUrl = data.data.message ?: ""
                    Picasso.get().load(imageUrl).into(binding.ivSearch)

                }

                is SearchState.Loading -> {

                }

                is SearchState.Error -> {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
    private fun setupSearchView() {
        binding.svDog.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.searchDogByBreed(it) // Envía la búsqueda al ViewModel
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Puedes manejar las búsquedas mientras el usuario escribe si lo deseas
                return false
            }
        })
    }


}