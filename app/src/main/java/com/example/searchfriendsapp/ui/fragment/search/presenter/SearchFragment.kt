package com.example.searchfriendsapp.ui.fragment.search.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
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
        setupOnClick()
//        navigation()

    }

    private fun searchObserver() {
        searchViewModel.searchState.observe(viewLifecycleOwner) { data ->
            when (data) {
                is SearchState.Success -> {
                    loadImage(data)
                    putExtra(imageUrl = data.data.message ?: "")

                }

                is SearchState.Loading -> {
                    showLoading()
                }

                is SearchState.Error -> {
                    hideLoading()
                    Toast.makeText(context, "Ups! no hay resultados", Toast.LENGTH_SHORT).show()
                    binding.ivSearch.setImageResource(R.drawable.img_not_result)
                }
            }
        }
    }

//    private fun navigation() {
//
//        binding.ivBack.setOnClickListener {
//            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
//        }
//        binding.tvBack.setOnClickListener {
//            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
//        }
//    }

    private fun putExtra(imageUrl: String) {
        binding.ivSearch.setOnClickListener {
            val bundle = Bundle().apply {
                putString("imageUrl", imageUrl)
            }
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment2, bundle)
        }
    }

    private fun loadImage(data: SearchState.Success) {
        val imageUrl = data.data.message ?: ""
        Picasso.get().load(imageUrl).into(binding.ivSearch)
    }

    private fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressCircular
            .visibility = View.GONE
    }

    private fun setupSearchView() {
        binding.svDog.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.searchDogByBreed(it.lowercase())
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
    }

    private fun setupOnClick() {
        // Deshabilitamos ambos botones al principio
        binding.btBackWhiteTermsAndConditions.isEnabled = false
        binding.btBackBlackTermsAndConditions.isEnabled = true

        // Configuramos el listener para el botón de regresar
        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false

            // Animamos el botón hacia la derecha
            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 500 // Ajuste a la duración de la animación

                // Cuando termine la animación, navegamos al HomeFragment
                withEndAction {
                    // Navegamos al homeFragment después de la animación
                    findNavController().navigate(R.id.action_searchFragment_to_homeFragment)

                    // Habilitamos el botón de nuevo por si el usuario regresa
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
        }
    }

}