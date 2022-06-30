package space.ecotours.ecotours_app.remote

object HttpRoutes {

    private const val BASE_URL = "http://ecotours.hopto.org:8080"
    const val TOUR = "$BASE_URL/tour/view/"
    const val TOUR_PHOTO = "$BASE_URL/tour/photo/"
    const val TOUR_LIST = "$BASE_URL/tour/list"
    const val LOGIN = "$BASE_URL/login"
    const val REGISTER = "$BASE_URL/register"
    const val BOOKING = "$BASE_URL/booking"
    const val BOOKING_LIST = "$BASE_URL/booking/"
}