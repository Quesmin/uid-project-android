package com.example.uid_project.data.repository

import com.example.uid_project.data.api.ApiService
import com.example.uid_project.data.api.PlanetApi

class MainRepository(private val planetApi: PlanetApi) {

    suspend fun getPlanetById(id: Int) = planetApi.getPlanetById(id)

}