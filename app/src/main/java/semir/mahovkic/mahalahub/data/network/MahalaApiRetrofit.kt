package semir.mahovkic.mahalahub.data.network

import retrofit2.http.Body
import retrofit2.http.POST
import semir.mahovkic.mahalahub.data.model.LoginDetails
import semir.mahovkic.mahalahub.data.network.model.LoginRequestDto

interface MahalaApiRetrofit {
    @POST("users/login")
    suspend fun generateLoginCode(@Body loginRequestDto: LoginRequestDto): LoginDetails
}