package pe.edu.ulima.aprendiendo.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.aprendiendo.R
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration
import pe.edu.ulima.aprendiendo.viewholders.PokemonListViewHolder

class PokemonListAdapter(): RecyclerView.Adapter<PokemonListViewHolder>(){
    var pokemonList: List<PokemonGeneration> = emptyList<PokemonGeneration>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonListViewHolder(layoutInflater.inflate(R.layout.rview_pokemon_list, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        Log.d("onBindViewHolder", holder.tvName.toString())
        val pokemon = pokemonList[position]
        holder.render(pokemon)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}