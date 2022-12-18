package com.example.uid_project.data.api

import com.example.uid_project.data.models.ResidentResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface ResidentApi {

    @GET("people/{id}")
    suspend fun getResidentById(
        @Path("id") id: Int
    ): ResidentResponseData
}