package space.ecotours.ecotours_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import space.ecotours.ecotours_app.EcoToursBar
import space.ecotours.ecotours_app.remote.PostsService
import space.ecotours.ecotours_app.remote.dto.RegisterRequest
import space.ecotours.ecotours_app.remote.dto.TokenResponse
import space.ecotours.ecotours_app.token


@Composable
fun checkRegisterScreen( navController: NavController, registerData: RegisterRequest? ) {
    if (registerData != null) {
        val service = PostsService.create()
        val dataRegister = RegisterRequest(
            phone = registerData.phone,
            password = registerData.password,
            name = registerData.name,
            email = registerData.email
        )
        val tokenResponse = produceState<TokenResponse>(
            initialValue = TokenResponse(token = ""),
            producer = {
                value = service.register(dataRegister)
            }
        )
        token = tokenResponse.value.token

        if (token != "") {
            navController.navigate("tourList")
        }
    }

    EcoToursBar()
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(color = MaterialTheme.colors.primary, text = "Загрузка...")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("register")
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