package com.example.uid_project.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.uid_project.data.models.PlanetResponseData
import com.example.uid_project.data.repository.MainRepository
import com.example.uid_project.ui.main.adapters.model.MainListItemPlanet
import com.example.uid_project.ui.main.mappers.MainMapper
import com.example.uid_project.utils.Resource
import kotlinx.coroutines.Dispatchers


const val END_PLANET_ID = 9;
const val START_PLANET_ID = 1;

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getPlanetById(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getPlanetById(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getSpecificRangeOfPlanets() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            var planetsResultArrayList: ArrayList<MainListItemPlanet> = ArrayList()
            for (i in START_PLANET_ID..END_PLANET_ID){
                val mappedPlanetResult = MainMapper.fromPlanetResponseToPlanetViewData( mainRepository.getPlanetById(i))
                planetsResultArrayList.add(mappedPlanetResult)
            }

            emit(Resource.success(data = planetsResultArrayList))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}