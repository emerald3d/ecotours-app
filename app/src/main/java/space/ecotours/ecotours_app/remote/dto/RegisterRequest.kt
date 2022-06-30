package space.ecotours.ecotours_app.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class RegisterRequest(
    var phone: String,
    var password: String,
    var name: String,
    var email: String?
) : Parcelable
