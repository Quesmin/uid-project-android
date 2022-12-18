package com.example.uid_project.ui.main.adapters.model

import android.graphics.drawable.Drawable

data class PlanetActivityData(
    val name: String,
    val population: String,
    val climate: String,
    val diameter: String,
    val gravity: String,
    val terrain: String,
    val residents: List<String>,
    val imageId: Int
)
