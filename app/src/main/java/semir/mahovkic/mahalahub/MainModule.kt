package semir.mahovkic.mahalahub

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import semir.mahovkic.mahalahub.data.BackendApi
import semir.mahovkic.mahalahub.data.network.MahalaBackendApi
import semir.mahovkic.mahalahub.data.network.MahalaApiRetrofit
import semir.mahovkic.mahalahub.data.network.getClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    private val _api = getClient().create(MahalaApiRetrofit::class.java)

    @Provides
    @Singleton
    fun providesBackendApi(): BackendApi = MahalaBackendApi(_api)

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}