package pe.edu.ulima.aprendiendo.activities.ui.app.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.ulima.aprendiendo.R

@Preview
@Composable
fun TopBarPreview(){
    TopBar()
}

@Composable
fun TopBar(){
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ){
        TopAppBar(
            modifier = Modifier.padding(
                bottom =  24.dp),
            backgroundColor = Color(R.color.purple_500),
            elevation = 0.dp,
            title = {
                Text(
                    text = "Aplicación Demo",
                    color = Color.White
                )
            },
            actions = {
                AppBarActions()
            }
        )
    }
}

@Composable
fun AppBarActions(){
    SearchAction()
    ShareAction()
    MoreAction()
}

@Composable
fun SearchAction(){
    val context = LocalContext.current
    IconButton(
        onClick = {
            Toast.makeText(context,"Buscar???", Toast.LENGTH_SHORT).show()
        }
    ){
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "icono buscar",
            tint = Color.White
        )
    }
}

@Composable
fun ShareAction(){
    val context = LocalContext.current
    IconButton(
        onClick = {
            Toast.makeText(context,"Compartir???", Toast.LENGTH_SHORT).show()
        }
    ){
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "icono compartir",
            tint = Color.White
        )
    }
}

@Composable
fun MoreAction(){
    val context = LocalContext.current
    IconButton(
        onClick = {
            Toast.makeText(context,"Más???", Toast.LENGTH_SHORT).show()
        }
    ){
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "icono más",
            tint = Color.White
        )
    }
}
