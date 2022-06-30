package space.ecotours.ecotours_app.screens

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
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
import kotlinx.android.parcel.Parcelize
import space.ecotours.ecotours_app.navigation.navigate
import space.ecotours.ecotours_app.remote.PostsService
import space.ecotours.ecotours_app.remote.dto.TourListResponse
import space.ecotours.ecotours_app.remote.dto.TourResponse

@Parcelize
data class Tour_id(val value: Int, val token: String): Parcelable

@Composable
fun TourListScreen(
    navController: NavController,
    token: String
) {
    

    val service = PostsService.create()
    val tours = produceState<TourListResponse>(
        initialValue = TourListResponse(listOf(TourResponse(0, "", "", "", "", "", "", 0, 0, ""))),
        producer = {
            value = service.getTourList()
        }
    )

    val sampleData = tours


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

        Column {
            sampleData.value?.let {
                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    items(it.tourList.size) {index ->
                        val tour_id = Tour_id(it.tourList[index].tour_id, token = token)
                        Card(
                            modifier = Modifier
                                .clickable( onClick = { navController.navigate("tour", bundleOf(Pair("TOUR_ID", tour_id))) })
                                .fillMaxWidth()
                                .padding(10.dp, 5.dp, 10.dp, 5.dp),
                            elevation = 5.dp,
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = "${it.tourList[index].name}",
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Left
                                )
                                Text(
                                    text = "        ${it.tourList[index].description.take(135)}...",
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Left
                                )
                                Card (shape = RoundedCornerShape(10.dp)) {
                                    AsyncImage(
                                        model = it.tourList[index].tourphoto_url,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Card() {
                                    Row (verticalAlignment = Alignment.CenterVertically) {
                                        Column(
                                            modifier = Modifier.padding(10.dp)
                                        ) {
                                            Text(
                                                text = "${it.tourList[index].region_name}",
                                                color = MaterialTheme.colors.secondary,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Light,
                                                textAlign = TextAlign.Left
                                            )
                                            Text(
                                                text = "${it.tourList[index].type_name}",
                                                color = MaterialTheme.colors.secondary,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Left
                                            )
                                        }
                                        Text(
                                            text = "₽" + it.tourList[index].price/1000 + ".000",
                                            color = MaterialTheme.colors.secondary,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Light,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Right
                                        )
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}