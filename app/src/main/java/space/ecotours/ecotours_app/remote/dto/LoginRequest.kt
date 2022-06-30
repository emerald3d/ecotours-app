package space.ecotours.ecotours_app.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class LoginRequest(
    var phone: String,
    var password: String
) : Parcelable
