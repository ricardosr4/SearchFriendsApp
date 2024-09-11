package com.example.searchfriendsapp.ui.fragment.puppies.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfriendsapp.R

class DogImageAdapter(private var images: List<String>) : RecyclerView.Adapter<DogImageAdapter.DogImageViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : DogImageViewHolder {
        val view = layoutInflater.from(parent.context).inflate(R.layout.item_dog_image, parent, false)
        return DogImageViewHolder(view)
    }


}