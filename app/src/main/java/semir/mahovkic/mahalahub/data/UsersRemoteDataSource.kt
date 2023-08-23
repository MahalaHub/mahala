package semir.mahovkic.mahalahub.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import semir.mahovkic.mahalahub.data.model.LoginDetails
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val backendApi: BackendApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun generateLoginCode(username: String, email: String): LoginDetails =
        withContext(ioDispatcher) {
            backendApi.generateLoginCode(username, email)
        }
}