package space.ecotours.ecotours_app.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class BookingRequest(
    val token: String,
    val tour_id: Int
): Parcelable

@Serializable
data class BookingEmptyResponse(
    val value: String
)
