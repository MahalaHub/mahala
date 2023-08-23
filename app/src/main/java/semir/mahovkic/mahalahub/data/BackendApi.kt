package semir.mahovkic.mahalahub.data

import semir.mahovkic.mahalahub.data.model.LoginDetails

interface BackendApi {
    suspend fun generateLoginCode(username: String, email: String): LoginDetails
}