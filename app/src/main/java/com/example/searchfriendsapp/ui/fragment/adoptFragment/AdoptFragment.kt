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

        //Recibe la URL de la imagen desde el Bundle
        val imageUrl = arguments?.getString("imageUrl")

        //Cargar la imagen en iv_adopt usando Piccasso
        imageUrl?.let {
            Picasso.get().load(it).into(binding.ivAdopt)
        }

        navigation()
    }
    private fun navigation(){
        binding.btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_adoptFragment_to_homeFragment)

        }
    }
}