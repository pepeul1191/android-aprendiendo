package pe.edu.ulima.aprendiendo.activities.ui.app.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.ulima.aprendiendo.activities.ui.app.viewmodels.PokemonListViewModel
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration
import coil.compose.rememberImagePainter

@Preview
@Composable
fun PokemonListScreenPreview() {
    PokemonListScreen(
        PokemonListViewModel()
    )
}

@Composable
public fun PokemonListScreen(
    viewModel: PokemonListViewModel
){
    val context = LocalContext.current as Activity
    val pokemonList: List<PokemonGeneration> by viewModel.pokemonList.observeAsState(initial = mutableListOf())

    viewModel.fetch("", context)

    Text(
        text = "Listado de pokemones"
    )
    LazyColumn(

    ){
        item{
            Text(
                text = "Nombre"
            )
        }
        items(pokemonList){
            val pokemon: PokemonGeneration = it
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = rememberImagePainter(data = pokemon.imageURL),
                        contentDescription = pokemon.name, // TODO: Provide a meaningful description
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp, 100.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(Color.Transparent),
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ){
                        Text(
                            text = "Número: ${pokemon.number}"
                        )
                        Text(
                            text = "Nombre: ${pokemon.name}"
                        )
                        Text(
                            text = "Altura: ${pokemon.height} m"
                        )
                        Text(
                            text = "Peso: ${pokemon.weight} kg"
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp, top = 0.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
        }
    }
}

