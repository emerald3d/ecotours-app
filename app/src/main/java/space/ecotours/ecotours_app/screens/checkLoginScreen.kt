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
import space.ecotours.ecotours_app.remote.dto.LoginRequest
import space.ecotours.ecotours_app.remote.dto.TokenResponse
import space.ecotours.ecotours_app.token

@Composable
fun checkLoginScreen(navController: NavController, loginData: LoginRequest) {
    if (loginData != null) {
        val service = PostsService.create()
        val datalogin = LoginRequest(phone = loginData.phone, password = loginData.password)
        val tokenResponse = produceState<TokenResponse>(
            initialValue = TokenResponse(token = ""),
            producer = {
                value = service.login(datalogin)
            }
        )
        token = tokenResponse.value.token

        if (token != "") {
            navController.navigate("tourList")
        } else if ( tokenResponse.value.token != "")
        {
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
                navController.navigate("login")
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