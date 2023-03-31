package pe.edu.ulima.aprendiendo.activities.ui.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.ulima.aprendiendo.R

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        goToSignIn = {},
        goToResetPassword = {},
    )
}

@Composable
public fun LoginScreen(
    goToSignIn: () -> Unit,
    goToResetPassword: () -> Unit
) {
    val context = LocalContext.current as Activity
    var userTState by remember { mutableStateOf(TextFieldValue()) }
    var passwordTState by remember { mutableStateOf(TextFieldValue()) }

    val onClick: () -> Unit = {
        context.finish()
    }
    // close
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "your image",
            modifier = Modifier.padding(16.dp)
                .align(Alignment.TopEnd)
                .clickable(onClick = onClick),
            colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
        )
    }
    // container
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Column(modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // logo
            Image(
                painterResource(id = R.drawable.ic_ulima),
                contentDescription ="Logo ULima",
                modifier = Modifier.size(125.dp).padding(bottom = 20.dp),
                colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
                contentScale = ContentScale.Fit,
            )
            // title
            Text(
                text = "Ingresar al Sistema",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            // usuario
            TextField(
                value = userTState,
                onValueChange = { userTState = it },
                label = { Text("Usuario") },
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
            // contraeña
            TextField(
                value = passwordTState,
                onValueChange = { passwordTState = it },
                label = { Text("Contraseña") },
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
            // login button
            Button(
                onClick = {},
                shape = CutCornerShape(0),
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
            ) {
                Text(("Ingresar").toUpperCase())
            }
            // login with google button
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
            // hr
            Divider(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 10.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
            // sign in button
            Button(
                onClick = { goToSignIn() },
                shape = CutCornerShape(0),
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth(),
            ) {
                Text(("Ir a Crear Cuenta").toUpperCase())
            }
            // reset password in button
            Button(
                onClick = { goToResetPassword() },
                shape = CutCornerShape(0),
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(),
            ) {
                Text(("Ir a Recuperar Contraseña").toUpperCase())
            }
        }
    }
}

