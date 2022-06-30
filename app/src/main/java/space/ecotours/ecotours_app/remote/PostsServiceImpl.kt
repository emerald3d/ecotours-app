package space.ecotours.ecotours_app.remote

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import space.ecotours.ecotours_app.remote.dto.*

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {

    override suspend fun login(logintRequest: LoginRequest): TokenResponse {
        return try {
            client.post<TokenResponse> {
                url(HttpRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                body = logintRequest
            }
        } catch(e: Exception) {
            TokenResponse(token = "")
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): TokenResponse {
        return try {
            client.post<TokenResponse> {
                url(HttpRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                body = registerRequest
            }
        } catch (e: Exception) {
            TokenResponse(token = "")
        }
    }

    override suspend fun getTourList(): TourListResponse {
        return try {
            client.get { url(HttpRoutes.TOUR_LIST) }
        } catch(e: java.lang.Exception) {
            TourListResponse(listOf(TourResponse(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                "")))
        }
    }

    override suspend fun getTour(tour_id: Int): TourResponse {
        return try {
            client.get { url("${HttpRoutes.TOUR}$tour_id") }
        } catch(e: java.lang.Exception) {
            TourResponse(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                "")
        }
    }

    override suspend fun getTourPhoto(tour_id: Int): TourPhotoListResponse {
        return try {
            client.get { url("${HttpRoutes.TOUR_PHOTO}$tour_id") }
        } catch(e: java.lang.Exception) {
            TourPhotoListResponse(listOf(TourPhotoResponse("")))
        }
    }

    override suspend fun booking(bookingRequest: BookingRequest): BookingEmptyResponse {
        return try {
            client.post<BookingEmptyResponse> {
                url(HttpRoutes.BOOKING)
                contentType(ContentType.Application.Json)
                body = BookingRequest(token = bookingRequest.token, tour_id = bookingRequest.tour_id)
            }
        } catch (e: Exception) {
            BookingEmptyResponse("")
        }
    }
}