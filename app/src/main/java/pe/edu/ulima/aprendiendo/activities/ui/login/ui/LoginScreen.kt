package pe.edu.ulima.aprendiendo.activities.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import pe.edu.ulima.aprendiendo.R

@Preview
@Composable
fun LoginScreen() {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
              Login(Modifier.align(Alignment.Center))
    }

}

@Composable
fun Login(modifier: Modifier){
    Column(modifier = modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HeaderImage()
        UserText("Usuario")
        UserText("Contraseña")
        LoginButton()
        GoogleButton()
        Divider(
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp, top = 5.dp),
            thickness = 2.dp,
            color = Color.Gray
        )
    }
}

@Composable
fun HeaderImage() {
    Image(
        painterResource(id = R.drawable.ic_ulima),
        contentDescription ="Logo ULima",
        modifier = Modifier.size(125.dp).padding(bottom = 20.dp),
        colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun UserText(labelText: String){
    var textState by remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = textState,
        onValueChange = { textState = it },
        label = { Text(labelText) },
        placeholder = { Text("") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        )
    )
}

@Composable
fun LoginButton() {
    Button(
        onClick = {},
        shape = CutCornerShape(0),
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
    ) {
        Text(("Ingresar").toUpperCase())
    }
}

@Preview
@Composable
fun GoogleButton() {
    Button(
        onClick = {},
        shape = CutCornerShape(0),
        modifier = Modifier
            .padding(top = 0.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
    ) {
        Image(
            painterResource(id = R.drawable.ic_google),
            contentDescription ="Cart button icon",
            modifier = Modifier.size(22.dp).padding(end = 10.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Text(
            ("Ingresar con Google").toUpperCase(),
            color = Color.White
        )
    }
}
