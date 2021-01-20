package com.example.test.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface AboutService {
    @GET("facts.json")
    suspend fun getAboutList(): About
}

object AboutNetwork {
    // To Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val aboutService = retrofit.create(AboutService::class.java)
}
