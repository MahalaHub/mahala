package semir.mahovkic.mahalahub.data

import semir.mahovkic.mahalahub.data.model.LoginDetails
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource
) {
    suspend fun generateLoginCode(username: String, email: String): LoginDetails {
        return remoteDataSource.generateLoginCode(username, email)
    }
}