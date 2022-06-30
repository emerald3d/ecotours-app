package space.ecotours.ecotours_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import space.ecotours.ecotours_app.remote.dto.BookingRequest
import space.ecotours.ecotours_app.remote.dto.LoginRequest
import space.ecotours.ecotours_app.remote.dto.RegisterRequest
import space.ecotours.ecotours_app.screens.*
import space.ecotours.ecotours_app.ui.theme.EcotoursappTheme

var token = "e0e709e2-1f0d-4d11-9221-85bba2b10556"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcotoursappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "first") {
                        composable("first") { FirstScreen(navController) }
                        composable("tourList") { TourListScreen(navController, token = token) }
                        composable("login") { LoginScreen(navController) }
                        composable("register") { RegisterScreen(navController) }
                        composable("checkLogin") { navController.previousBackStackEntry?.arguments?.getParcelable<LoginRequest>("LOGIN_DATA")?.let {
                            checkLoginScreen(navController, it)
                        } }
                        composable("checkRegister") { navController.previousBackStackEntry?.arguments?.getParcelable<RegisterRequest>("REGISTER_DATA")?.let {
                            checkRegisterScreen(navController, it)
                        } }
                        composable("checkBooking") { navController.previousBackStackEntry?.arguments?.getParcelable<BookingRequest>("BOOKING_DATA")?.let {
                            checkBookingScreen(navController, it)
                        } }
                        composable("tour") { navController.previousBackStackEntry?.arguments?.getParcelable<Tour_id>("TOUR_ID")?.let {
                            TourScreen(navController, it)
                        } }
                    }
                    navController.navigate("first")
                }
            }
        }
    }
}

@Composable
fun EcoToursBar() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                .padding(30.dp)
        ) {
            Text(
                text = "экотуры",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}