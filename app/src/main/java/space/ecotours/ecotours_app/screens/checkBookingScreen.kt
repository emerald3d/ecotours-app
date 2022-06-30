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
import space.ecotours.ecotours_app.remote.dto.BookingEmptyResponse
import space.ecotours.ecotours_app.remote.dto.BookingRequest

val token = ""

@Composable
fun checkBookingScreen(navController: NavController, bookingData: BookingRequest) {
    if (bookingData != null) {
        val service = PostsService.create()
        val dataBooking = BookingRequest(token = bookingData.token, tour_id = bookingData.tour_id)
        val bookingEmptyResponse = produceState<BookingEmptyResponse>(
            initialValue = BookingEmptyResponse(value = ""),
            producer = {
                value = service.booking(dataBooking)
            }
        )
    }

    EcoToursBar()
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(color = MaterialTheme.colors.primary, text = "Успех")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("tourList")
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