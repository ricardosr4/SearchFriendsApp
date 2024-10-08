package com.example.searchfriendsapp.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.ItemRvHomeBinding
import com.squareup.picasso.Picasso

class AdapterHome(
    private val listHome: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.render(listHome[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return listHome.size
    }
}

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRvHomeBinding.bind(view)

    fun render(image: String, onItemClick: (String) -> Unit) {
        Picasso.get().load(image).into(binding.ivItemRecyclerHome)
        itemView.setOnClickListener { onItemClick(image) }
    }
}
