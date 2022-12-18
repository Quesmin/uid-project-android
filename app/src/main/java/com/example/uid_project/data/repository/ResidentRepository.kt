package com.example.uid_project.data.repository

import com.example.uid_project.data.api.PlanetApi
import com.example.uid_project.data.api.ResidentApi

class ResidentRepository(private val residentApi: ResidentApi) {

    suspend fun getResidentById(id: Int) = residentApi.getResidentById(id)

}