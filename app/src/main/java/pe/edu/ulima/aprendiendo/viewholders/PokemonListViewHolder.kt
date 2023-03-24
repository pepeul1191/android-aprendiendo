package pe.edu.ulima.aprendiendo.viewholders

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.ulima.aprendiendo.R
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration

class PokemonListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val view = view
    val ivImageURL = view.findViewById<ImageView>(R.id.ivImageURL)
    val tvNumber = view.findViewById<TextView>(R.id.tvNumber)
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvWeight = view.findViewById<TextView>(R.id.tvWeight)
    val tvHeight = view.findViewById<TextView>(R.id.tvHeight)

    fun render(pokemon: PokemonGeneration){
        tvNumber.setText(pokemon.number.toString())
        tvName.setText(pokemon.name.toString())
        tvWeight.setText("${pokemon.weight.toString()} kg")
        tvHeight.setText("${pokemon.height.toString()} cm")
        Glide.with(view.context)
            .load(pokemon.imageURL)
            .into(ivImageURL)
    }

    fun resetState() {

    }
}