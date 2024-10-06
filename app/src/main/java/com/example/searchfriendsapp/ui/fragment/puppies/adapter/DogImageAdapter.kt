package com.example.searchfriendsapp.ui.fragment.puppies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfriendsapp.R
import com.squareup.picasso.Picasso

class DogImageAdapter(private var images: List<String>,
//Nuevo recibe una funcion para manejar el clic
private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<DogImageAdapter.ViewHolder>() {

        fun updateData(newImages: List<String>?){
            if (newImages != null) {
                images = newImages
            }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_puppies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //nuevo
        val imageUrl = images[position]
        holder.bind(imageUrl)

        //Se agrega el listener para dar clic en la imagen
        holder.itemView.setOnClickListener {
            onItemClick(imageUrl) //Llamar a la funcion de clic con la URL de la Imagen
        }

    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(itemView : View) :  RecyclerView.ViewHolder(itemView){
        private val imageView: ImageView = itemView.findViewById(R.id.item_puppies)
        fun bind(imageUrl: String){
            Picasso.get().load(imageUrl).into(imageView)
        }
    }


}