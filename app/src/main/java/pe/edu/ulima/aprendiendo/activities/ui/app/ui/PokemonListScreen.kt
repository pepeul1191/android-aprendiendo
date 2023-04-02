package pe.edu.ulima.aprendiendo.activities.ui.app.ui

import android.app.Activity
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun PokemonListScreenPreview() {
    PokemonListScreen(
        PokemonListViewModel()
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun PokemonListScreen(
    viewModel: PokemonListViewModel
){
    val context = LocalContext.current as Activity
    val pokemonList: List<PokemonGeneration> by viewModel.pokemonList.observeAsState(initial = mutableListOf())
    LaunchedEffect(Unit, block = {
        viewModel.fetch("", context)
    })
    // bottom sheet - values
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy
        )
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    
    Scaffold(
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            floatingButton(scope, sheetState)
        }
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                sheetFilters()
            },
            sheetPeekHeight = 0.dp
        ){
            pokemonListView(pokemonList)
        }
    }
}

@Composable
fun sheetFilters(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp)
    ){
        Text("hola mundo")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun floatingButton(scope: CoroutineScope, sheetState: BottomSheetState ){
    FloatingActionButton(
        onClick = {
        }
    ) {
        IconButton(
            onClick = {
                scope.launch{
                    if(sheetState.isCollapsed){
                        sheetState.expand()
                    }else {
                        sheetState.collapse()
                    }
                }
            },
        ){
            Icon(Icons.Default.Settings, "Filtrar Pokemones")
        }
    }
}

@Composable
fun pokemonListView(pokemonList: List<PokemonGeneration>){
    LazyColumn(
    ){
        item{
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
