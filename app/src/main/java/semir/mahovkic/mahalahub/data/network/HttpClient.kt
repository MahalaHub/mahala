package semir.mahovkic.mahalahub.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import semir.mahovkic.mahalahub.BuildConfig

fun getClient(): Retrofit {
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val mahalaApi = BuildConfig.MAHALA_API

    return Retrofit.Builder()
        .baseUrl(mahalaApi)
        .addConverterFactory(GsonConverterFactory.create())
//        .client(client)
        .build()
}