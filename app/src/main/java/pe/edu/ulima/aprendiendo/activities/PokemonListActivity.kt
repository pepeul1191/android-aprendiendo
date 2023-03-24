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
import pe.edu.ulima.aprendiendo.models.responses.PokemonListResponse
import pe.edu.ulima.aprendiendo.viewmodels.PokemonListViewModel

class PokemonListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonListBinding
    private val adapter = PokemonListAdapter()
    private val activity = this
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
        // events
        binding.etPokemonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the text variable with the new text
                val text = s.toString()
                // Do something with the text
                // Log.d("onTextChanged", "Text: $text")
                pokemonListViewModel.fetch(text, activity)
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
        binding.bPokemonTypes.setOnClickListener(){
            val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            val checkedItems = booleanArrayOf(false, false, false, false, false)

            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Choose items")
            builder.setMultiChoiceItems(items, checkedItems) { dialog, which, isChecked ->
                checkedItems[which] = isChecked
            }
            builder.setPositiveButton("OK") { dialog, which ->
                // Handle OK button click
                val selectedItems = mutableListOf<String>()
                for (i in items.indices) {
                    if (checkedItems[i]) {
                        selectedItems.add(items[i])
                    }
                }
                Toast.makeText(activity, "Selected items: ${selectedItems.joinToString(", ")}", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel", null)

            val dialog = builder.create()
            dialog.show()
        }
    }
}