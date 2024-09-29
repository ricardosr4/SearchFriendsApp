package com.example.searchfriendsapp.ui.fragment.us.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.data.response.UsResponse
import com.example.searchfriendsapp.databinding.ItemRvAboutUsBinding
import com.squareup.picasso.Picasso

class AboutUsAdapter(private val aboutUsList: List<UsResponse>):RecyclerView.Adapter<AboutUsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutUsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_about_us, parent, false)
        return AboutUsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aboutUsList.size
    }

    override fun onBindViewHolder(holder: AboutUsViewHolder, position: Int) {
        holder.render(aboutUsList[position])
    }
}

class AboutUsViewHolder(view: View):RecyclerView.ViewHolder(view){
    val binding = ItemRvAboutUsBinding.bind(view)
    fun render(usResponse: UsResponse){
        Picasso.get().load(usResponse.image).into(binding.ivAboutUs)

    }

}