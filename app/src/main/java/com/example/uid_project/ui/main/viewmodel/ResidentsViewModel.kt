package com.example.uid_project.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.uid_project.data.repository.MainRepository
import com.example.uid_project.data.repository.ResidentRepository
import com.example.uid_project.ui.main.adapters.model.MainListItemPlanet
import com.example.uid_project.ui.main.adapters.model.ResidentListItemData
import com.example.uid_project.ui.main.mappers.MainMapper
import com.example.uid_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class ResidentsViewModel(private val residentRepository: ResidentRepository) : ViewModel() {

//    fun getPlanetById(id: Int) = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = mainRepository.getPlanetById(id)))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    fun getResidentsFromIdArray(residentsIds: Array<Int>) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val residentResultArray: ArrayList<ResidentListItemData> = ArrayList()

            residentsIds.forEach {
                val fetchedResident = residentRepository.getResidentById(it)
                val listItemData = ResidentListItemData(
                    fetchedResident.name,
                    fetchedResident.birth_year,
                    fetchedResident.height,
                    fetchedResident.mass
                )

                Log.e("data element: ", listItemData.toString())
                Log.e("data array ", residentResultArray.toString())
                residentResultArray.add(listItemData)
            }

            Log.e("data array ", residentResultArray.toString())

//            for (i in START_PLANET_ID..END_PLANET_ID) {
//                val mappedPlanetResult =
//                    MainMapper.fromPlanetResponseToPlanetViewData(mainRepository.getPlanetById(i))
//                planetsResultArrayList.add(mappedPlanetResult)
//            }

            emit(Resource.success(data = residentResultArray))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}