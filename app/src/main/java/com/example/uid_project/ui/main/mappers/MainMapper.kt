package com.example.uid_project.ui.main.mappers

import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.example.uid_project.data.models.PlanetResponseData
import com.example.uid_project.ui.main.adapters.MainListAdapter
import com.example.uid_project.ui.main.adapters.model.MainListItemPlanet
import com.example.uid_project.ui.main.adapters.model.PlanetActivityData

class MainMapper {

    companion object {
        fun fromPlanetResponseToPlanetViewData(planetResponseData: PlanetResponseData): MainListItemPlanet {
            return MainListItemPlanet(
                planetResponseData.name,
                planetResponseData.population,
                planetResponseData.climate,
                planetResponseData.diameter,
                planetResponseData.gravity,
                planetResponseData.terrain,
                planetResponseData.residents,
                false
            )
        }

        fun fromPlanetMainItemToPlanetActivityData(mainPlanedData: MainListItemPlanet, imageId: Int): PlanetActivityData {
            return PlanetActivityData(
                mainPlanedData.name,
                mainPlanedData.population,
                mainPlanedData.climate,
                mainPlanedData.diameter,
                mainPlanedData.gravity,
                mainPlanedData.terrain,
                mainPlanedData.residents,
                imageId
            )
        }

    }
}