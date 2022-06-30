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
import space.ecotours.ecotours_app.remote.dto.RegisterRequest

var registerData: RegisterRequest = RegisterRequest(phone = "", password = "", name = "", email = "")

@Composable
fun RegisterScreen( navController: NavController) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    EcoToursBar()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                label = { Text (text = "Имя") },
                value = name,
                onValueChange = { name = it })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(40.dp, 0.dp, 40.dp, 0.dp),
                label = { Text (text = "Номер телефона +7") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                value = phone.take(10),
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
            OutlinedTextField(
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(40.dp, 0.dp, 40.dp, 0.dp),
                label = { Text (text = "Повтор пароля") },
                value = password2,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password2 = it })
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(40.dp, 0.dp, 40.dp, 0.dp),
                label = { Text (text = "Почта") },
                value = email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { email = it })
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (password == password2) {
                        registerData.phone = phone
                        registerData.password = password
                        registerData.name = name
                        registerData.email = email
                        navController.navigate("checkRegister", bundleOf(Pair("REGISTER_DATA", registerData)))
                    } else {
                        navController.navigate("checkRegister", bundleOf(Pair("REGISTER_DATA", registerData)))
                    }
                          },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(59.dp)
                    .padding(40.dp, 0.dp, 40.dp, 0.dp)
            ) {
                Text(text = "Регистрация")
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
}