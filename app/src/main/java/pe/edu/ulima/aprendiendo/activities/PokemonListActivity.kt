package pe.edu.ulima.aprendiendo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.ulima.aprendiendo.adapters.PokemonListAdapter
import pe.edu.ulima.aprendiendo.databinding.ActivityPokemonListBinding
import pe.edu.ulima.aprendiendo.models.beans.GenerationOption
import pe.edu.ulima.aprendiendo.models.responses.PokemonListResponse
import pe.edu.ulima.aprendiendo.viewmodels.PokemonListViewModel

class PokemonListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonListBinding
    private val adapter = PokemonListAdapter()
    private val activity = this
    private var generationOptions = listOf<GenerationOption>()
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
        pokemonListViewModel.generationList.observe(this) {items ->
            generationOptions = items
        }
        // fecth and list
        pokemonListViewModel.fetch("", this)
        // events
        binding.etPokemonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                pokemonListViewModel.fetch(text, activity)
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
        binding.bPokemonTypes.setOnClickListener(){
             // options
            val items: Array<String> = (generationOptions.map { it.name }).toTypedArray()
            val checkedItems: BooleanArray = (generationOptions.map { it.selected }).toTypedArray().toBooleanArray()
            // dialog
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Filtro por Generación")
            builder.setMultiChoiceItems(items, checkedItems) { dialog, which, isChecked ->
                checkedItems[which] = isChecked
            }
            builder.setPositiveButton("Aceptar") { dialog, which ->
                val selectedItems = mutableListOf<String>()
                val unSelectedItems = mutableListOf<String>()
                for (i in items.indices) {
                    if (checkedItems[i]) {
                        selectedItems.add(items[i])
                    }else{
                        unSelectedItems.add(items[i])
                    }
                }
                Toast.makeText(activity, "Selected items: ${selectedItems.joinToString(", ")}", Toast.LENGTH_SHORT).show()
                // update model
                pokemonListViewModel.updateGenerations(selectedItems.toList(), unSelectedItems.toList())
                pokemonListViewModel.fetch(binding.etPokemonName.text.toString(), activity)
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        }
    }
}