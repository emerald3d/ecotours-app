package space.ecotours.ecotours_app.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class BookingResponse(
    val date: String,
    val phone: String,
    val tour_id: String
): Parcelable

@Serializable
data class BookingListResponse(
    val bookingList: List<BookingResponse>
)
