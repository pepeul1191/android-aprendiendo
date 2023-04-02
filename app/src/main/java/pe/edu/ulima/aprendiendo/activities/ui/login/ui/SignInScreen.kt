package pe.edu.ulima.aprendiendo.activities.ui.login.ui

import android.app.Activity
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mukesh.MarkDown
import kotlinx.coroutines.launch
import pe.edu.ulima.aprendiendo.R
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.SignInViewModel
import java.net.URL

@ExperimentalMaterialApi
@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        SignInViewModel(),
        goToLogin = {},
        goToResetPassword = {},
    )
}

@ExperimentalMaterialApi
@Composable
public fun SignInScreen(
    viewModel: SignInViewModel,
    goToLogin: () -> Unit,
    goToResetPassword: () -> Unit
){
    // sign form - values
    val context = LocalContext.current as Activity
    val user: String by viewModel.user.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val password2: String by viewModel.password2.observeAsState(initial = "")
    //var emailTState by remember { mutableStateOf(TextFieldValue()) }
    //var userTState by remember { mutableStateOf(TextFieldValue()) }
    //var passwordTState by remember { mutableStateOf(TextFieldValue()) }
    //var password2TState by remember { mutableStateOf(TextFieldValue()) }
    var termsCkState by remember { mutableStateOf(false) }
    var termsVisible by remember { mutableStateOf(false) }
    // bottom sheet - values
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy
        )
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    // bottom sheet
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp)
                    .padding(10.dp)
            ){
                /*
                MarkDown(
                    url = URL("https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/main/README.md"),
                    modifier = Modifier.fillMaxSize()
                )
                */
                Text(
                    text = "Términos y Condiciones",
                    modifier = Modifier.align(Alignment.TopCenter).padding(5.dp),
                    style = TextStyle(fontSize = 20.sp),
                    color = Color(0xFFDF2853)
                )
                Row(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Button(
                        onClick = {
                            termsCkState = true
                            scope.launch{
                            sheetState.collapse()
                            } },
                        modifier = Modifier.weight(1f).padding(5.dp),
                        shape = CutCornerShape(0),
                    ) {
                        Text(text = "Acepto")
                    }
                    Button(
                        onClick = {
                            termsCkState = false
                            scope.launch{
                                sheetState.collapse()
                            }
                        },
                        modifier = Modifier.weight(1f).padding(5.dp),
                        shape = CutCornerShape(0),
                    ) {
                        Text(text = "No Acpeto")
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        // close
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "your image",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
                    .clickable(onClick = {
                        context.finish()
                    }),
                colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
            )
        }
        // container
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
            Column(modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // logo
                Image(
                    painterResource(id = R.drawable.ic_ulima),
                    contentDescription ="Logo ULima",
                    modifier = Modifier
                        .size(125.dp)
                        .padding(bottom = 20.dp),
                    colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
                    contentScale = ContentScale.Fit,
                )
                // title
                Text(
                    text = "Crear Cuenta",
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                // email
                TextField(
                    value = email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text("Correo Electrónico") },
                    placeholder = { Text("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    enabled = termsCkState,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                // usuario
                TextField(
                    value = user,
                    onValueChange = { viewModel.updateUser(it) },
                    label = { Text("Usuario") },
                    placeholder = { Text("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    enabled = termsCkState
                )
                // contraeña
                TextField(
                    value = password,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text("Contraseña") },
                    placeholder = { Text("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    enabled = termsCkState,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                // contraeña2
                TextField(
                    value = password2,
                    onValueChange = { viewModel.updatePassword2(it) },
                    label = { Text("Repita Contraseña") },
                    placeholder = { Text("") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    enabled = termsCkState,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                // checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Checkbox(
                        checked = termsCkState,
                        onCheckedChange = {
                            // termsCkState = it
                            scope.launch{
                                if(sheetState.isCollapsed){
                                    sheetState.expand()
                                }else {
                                    sheetState.collapse()
                                }
                            }
                        }
                    )
                    Text(
                        text = "Acepto los térmnos y condiciones",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                // sign in button
                Button(
                    onClick = {
                        viewModel.signIn(context)
                    },
                    shape = CutCornerShape(0),
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    enabled = termsCkState
                ) {
                    Text(("Crear Cuenta").toUpperCase())
                }
                // hr
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp),
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
}


