package space.ecotours.ecotours_app.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TokenResponse(
    val token: String
) : Parcelable
