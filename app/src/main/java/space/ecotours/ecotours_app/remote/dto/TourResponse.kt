package space.ecotours.ecotours_app.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TourResponse(
    val tour_id: Int,
    val name: String,
    val description: String,
    val type_name: String,
    val region_name: String,
    val date_start: String,
    val date_end: String,
    val price: Int,
    val max_capacity: Int,
    val tourphoto_url: String,
)

@Serializable
data class TourListResponse(
    val tourList: List<TourResponse>
)
