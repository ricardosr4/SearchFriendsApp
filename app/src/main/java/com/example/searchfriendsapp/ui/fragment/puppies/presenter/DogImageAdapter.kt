package com.example.searchfriendsapp.ui.fragment.puppies.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfriendsapp.R
import com.squareup.picasso.Picasso

abstract class DogImageAdapter(private var images: List<String>) :
    RecyclerView.Adapter<DogImageAdapter.DogImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_puppies, parent, false)
        return DogImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    class DogImageViewHolder(view : View) :  RecyclerView.ViewHolder(view){
        fun bind(imageUrl: String){
            Picasso.get().load(imageUrl).into(itemView.item_rv_puppies)
        }
    }


}