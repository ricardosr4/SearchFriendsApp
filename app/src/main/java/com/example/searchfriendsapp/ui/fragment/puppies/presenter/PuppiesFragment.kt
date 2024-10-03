package com.example.searchfriendsapp.ui.fragment.puppies.presenter

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentPuppiesBinding
import com.example.searchfriendsapp.ui.fragment.puppies.adapter.DogImageAdapter
import com.example.searchfriendsapp.ui.fragment.puppies.state.PuppiesState
import com.example.searchfriendsapp.ui.fragment.puppies.viewModel.DogViewModel

class PuppiesFragment : Fragment() {

    private var _binding: FragmentPuppiesBinding? = null
    private val binding get() = _binding!! //Solo usamos binding cuando no es nulo

    private val viewModel: DogViewModel by activityViewModels()
    private lateinit var adapter: DogImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPuppiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DogImageAdapter(emptyList())
        binding.rvPuppies.layoutManager = GridLayoutManager(context, 3)
        binding.rvPuppies.adapter = adapter

        call()
        setupOnClick()
        animateFootprint()

        //Observa el estado del ViewModel
        observeViewModelState()

        //Configurar el botón 'tv_puppies' para cargar las imagenes
        binding.tvFriend.setOnClickListener{
            viewModel.fetchDogImages() //carga las imagenes por primera vez
        }

        //Congigurar el botón 'images_paw' para recargar las imágenes
        binding.imagePaw.setOnClickListener {
            animateFootprint()//Animación para el botón
            viewModel.fetchDogImages() //Recargar las imágenes
        }


    }


    private fun animateFootprint() {
        binding.imagePaw.setOnClickListener {
            val animator = ValueAnimator.ofFloat(0f, 360f)
            animator.addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                binding.imagePaw.rotationY = animatedValue
            }
            animator.duration = 1000
            animator.start()

            //Aquí se llama a la función del ViewModel para refrescar las imágenes
            viewModel.fetchDogImages() //Llama al ViewModel para recargar imágenes
        }
    }

    private fun observeViewModelState(){
        viewModel.images.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PuppiesState.Loading -> {
                    //Mostrar ProgressBar y ocultar el RecyclerView
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvPuppies.visibility = View.GONE
                }
                is PuppiesState.Success -> {
                    //Mostrar las imagenes y ocultar ProgressBar
                    binding.progressBar.visibility = View.GONE
                    binding.rvPuppies.visibility = View.VISIBLE
                    adapter.updateData((state.success.message))
                }
                is PuppiesState.Error -> {
                    //Ocultar ProgressBar y Mostrar un mensaje de error
                    binding.progressBar.visibility = View.GONE
                    binding.rvPuppies.visibility = View.GONE
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //Manejar el caso 'null' o cualquier otro no cubierto
                    binding.progressBar.visibility = View.GONE
                    binding.rvPuppies.visibility = View.GONE
                }
            }

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
                    findNavController().navigate(R.id.action_puppiesFragment_to_homeFragment)
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
        }
    }
    private fun call(){
        viewModel.fetchDogImages()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}