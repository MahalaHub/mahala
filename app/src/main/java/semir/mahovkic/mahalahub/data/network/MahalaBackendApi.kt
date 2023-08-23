package semir.mahovkic.mahalahub.data.network

import semir.mahovkic.mahalahub.data.BackendApi
import semir.mahovkic.mahalahub.data.model.LoginDetails
import semir.mahovkic.mahalahub.data.network.model.LoginRequestDto
import javax.inject.Inject

class MahalaBackendApi @Inject constructor(
    private val api: MahalaApiRetrofit
) : BackendApi {
    override suspend fun generateLoginCode(
        username: String,
        emailOrPhoneNumber: String
    ): LoginDetails {
        return api.generateLoginCode(LoginRequestDto(username, emailOrPhoneNumber))
    }
}