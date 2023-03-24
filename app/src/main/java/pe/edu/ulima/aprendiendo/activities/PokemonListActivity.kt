package pe.edu.ulima.aprendiendo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.ulima.aprendiendo.adapters.PokemonListAdapter
import pe.edu.ulima.aprendiendo.databinding.ActivityPokemonListBinding
import pe.edu.ulima.aprendiendo.models.responses.PokemonListResponse
import pe.edu.ulima.aprendiendo.viewmodels.PokemonListViewModel

class PokemonListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonListBinding
    private val adapter = PokemonListAdapter()
    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // recycler view
        val recyclerView = this.binding.rvPokemons
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        // observer
        pokemonListViewModel.items.observe(this) { items ->
            adapter.pokemonList = items
            adapter.notifyDataSetChanged()
        }
        // fecth and list
        pokemonListViewModel.fetch("", this)
    }
}