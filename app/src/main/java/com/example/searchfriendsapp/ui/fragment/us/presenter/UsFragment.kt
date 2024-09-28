package com.example.searchfriendsapp.ui.fragment.us.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.FragmentUsBinding
import com.example.searchfriendsapp.ui.fragment.us.adapter.MembersAdapter
import com.example.searchfriendsapp.ui.fragment.us.provider.MemberProvider

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
    ): View {
        binding = FragmentUsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerMembers()
        setupOnClick()
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_usFragment_to_homeFragment)
        }
    }
    private fun initRecyclerMembers(){
        val usAdapter = MembersAdapter(MemberProvider.getMember())
        binding.rvMembers.adapter = usAdapter
    }

    private fun setupOnClick() {
        binding.btBackWhiteTermsAndConditions.isEnabled = false
        binding.btBackBlackTermsAndConditions.isEnabled = true
        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false
            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(-300f) //se cambia a negativo para mover a la izquierda
                interpolator = AccelerateDecelerateInterpolator()
                duration = 500
                withEndAction {
                    findNavController().navigate(R.id.action_usFragment_to_homeFragment)
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
        }
    }
}