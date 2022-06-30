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

@Composable
fun FirstScreen( navController: NavController ) {
    val service = PostsService.create()
    val datalogin = LoginRequest(phone = "9991117745", password = "MegaGuRR3n22LEADER")
    val token = produceState<TokenResponse>(
        initialValue = TokenResponse(token = ""),
        producer = {
            value = service.login(datalogin)
        }
    )
    EcoToursBar()
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = { navController.navigate("login") },
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
            onClick = { navController.navigate("register") },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
        ) {
            Text(text = "Регистрация")
        }
    }
}