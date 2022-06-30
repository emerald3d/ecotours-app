package space.ecotours.ecotours_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.AsyncImage
import space.ecotours.ecotours_app.navigation.navigate
import space.ecotours.ecotours_app.remote.PostsService
import space.ecotours.ecotours_app.remote.dto.BookingRequest
import space.ecotours.ecotours_app.remote.dto.TourResponse

@Composable
fun TourScreen(navController: NavController, tour_id: Tour_id) {
    val service = PostsService.create()
    val tour = produceState<TourResponse>(
        initialValue = TourResponse(0, "", "", "", "", "", "", 0, 0, ""),
        producer = {
            value = service.getTour(tour_id.value)
        }
    )

    val tourData = tour.value

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

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())) {
            Card (modifier = Modifier
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp)) {
                AsyncImage(
                    model = tourData.tourphoto_url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "${tourData.type_name}",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
                Text(
                    text = "${tourData.region_name}",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
            }
            Text(
                text = "${tourData.name}",
                color = MaterialTheme.colors.secondary,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Left
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "в период с " + tourData.date_start + " по " + tourData.date_end,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.secondary
                )
            }
            Button(
                onClick = {
                    navController.navigate("checkBooking", bundleOf(Pair("BOOKING_DATA", BookingRequest(token = tour_id.token, tour_id = tour_id.value))))
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
            ) {
                Text(
                    text = "Забронировать за ₽" + tourData.price/1000 + ".000",
                    color = Color.White,
                )
            }
            if (tourData.max_capacity<10) {
                Text(
                    text = "      Количество оставшихся мест: ${tourData.max_capacity}",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = "        ${tourData.description}",
                color = MaterialTheme.colors.secondary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Left
            )
        }
    }
}