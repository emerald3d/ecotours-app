package space.ecotours.ecotours_app.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TourPhotoListResponse(
    val tourPhotoList: List<TourPhotoResponse>
)

@Serializable
data class TourPhotoResponse(
    val url: String
)
