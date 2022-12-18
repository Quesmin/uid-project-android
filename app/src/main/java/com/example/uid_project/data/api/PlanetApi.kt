package com.example.uid_project.data.api

import com.example.uid_project.data.models.PlanetResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {

    @GET("planets/{id}")
    suspend fun getPlanetById(
        @Path("id") id: Int
    ): PlanetResponseData
}