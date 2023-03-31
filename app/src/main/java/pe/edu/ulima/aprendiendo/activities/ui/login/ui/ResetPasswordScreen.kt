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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import pe.edu.ulima.aprendiendo.R


@Preview
@Composable
fun ResetPasswordScreenPreview() {
    ResetPasswordScreen(
        goToLogin = {},
        goToSignIn = {}
    )
}

@Composable
fun ResetPasswordScreen(
    goToLogin: () -> Unit,
    goToSignIn: () -> Unit
) {
    val context = LocalContext.current as Activity
    var emailTState by remember { mutableStateOf(TextFieldValue()) }

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
                text = "Generar Nueva Contraseña",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            // correo
            TextField(
                value = emailTState,
                onValueChange = { emailTState = it },
                label = { Text("Correo") },
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
                Text(("Enviar Correo").toUpperCase())
            }
            // hr
            Divider(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 10.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
            // login in button
            Button(
                onClick = { goToLogin() },
                shape = CutCornerShape(0),
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth(),
            ) {
                Text(("Ir a Ingresar al Sistema").toUpperCase())
            }
            // sign in button
            Button(
                onClick = { goToSignIn() },
                shape = CutCornerShape(0),
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(),
            ) {
                Text(("Ir a Crear Cuenta").toUpperCase())
            }
        }
    }
}
