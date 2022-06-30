package space.ecotours.ecotours_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import space.ecotours.ecotours_app.EcoToursBar
import space.ecotours.ecotours_app.navigation.navigate
import space.ecotours.ecotours_app.remote.dto.LoginRequest


var loginData: LoginRequest = LoginRequest(phone = "9991117744", password = "MegaGuRR3n22LEADER")

@Composable
fun LoginScreen( navController: NavController) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    EcoToursBar()
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp),
            label = { Text (text = "Номер телефона +7") },
            value = phone.take(10),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { phone = it.take(10) })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp),
            label = { Text (text = "Пароль") },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password = it })
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                loginData.phone = phone
                loginData.password = password
                navController.navigate("checkLogin", bundleOf(Pair("LOGIN_DATA", loginData)))
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
        ) {
            Text(text = "Вход")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("first")
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
        ) {
            Text(text = "Назад")
        }
    }
}