package com.example.searchfriendsapp.ui.fragment.puppies.presenter

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentPuppiesBinding

class PuppiesFragment : Fragment() {
    //private lateinit var binding: FragmentPuppiesBinding
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

        setupOnClick()
        animateFootprint()



        adapter = DogImageAdapter(emptyList())
        binding.rvPuppies.layoutManager = GridLayoutManager(context, 3)
        binding.rvPuppies.adapter = adapter

        viewModel.images.observe(viewLifecycleOwner) { images ->
            adapter.updateData(images)

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
        }
    }

    private fun setupOnClick() {

        binding.btBackWhiteTermsAndConditions.isEnabled = false
        binding.btBackBlackTermsAndConditions.isEnabled = true


        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false


            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 500


                withEndAction {

                    findNavController().navigate(R.id.action_puppiesFragment_to_homeFragment)
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //Limpiar el binding cuando la vista es destruida.

    }
}