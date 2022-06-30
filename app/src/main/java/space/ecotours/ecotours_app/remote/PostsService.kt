package space.ecotours.ecotours_app.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import space.ecotours.ecotours_app.remote.dto.*

interface PostsService {

    suspend fun login(logintRequest: LoginRequest): TokenResponse

    suspend fun register(registerRequest: RegisterRequest): TokenResponse

    suspend fun getTourList(): TourListResponse

    suspend fun getTour(tour_id: Int): TourResponse

    suspend fun getTourPhoto(tour_id: Int): TourPhotoListResponse

    suspend fun booking(bookingRequest: BookingRequest): BookingEmptyResponse

    companion object {
        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}