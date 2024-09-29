package com.example.searchfriendsapp.ui.fragment.us.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.data.response.UsResponse
import com.example.searchfriendsapp.databinding.ItemMembersBinding
import com.squareup.picasso.Picasso

class MembersAdapter(private val listMembers:List<UsResponse>):RecyclerView.Adapter<MembersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_members,parent,false)
        return MembersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMembers.size
    }

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        holder.render(listMembers[position])
    }
}

class MembersViewHolder(view: View):RecyclerView.ViewHolder(view){
    val binding = ItemMembersBinding.bind(view)
    fun render(usMemberResponse: UsResponse){
        Picasso.get().load(usMemberResponse.image).into(binding.ivMember)

    }
}