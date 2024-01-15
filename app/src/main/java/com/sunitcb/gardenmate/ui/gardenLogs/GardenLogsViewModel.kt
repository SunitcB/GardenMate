package com.sunitcb.gardenmate.ui.gardenLogs

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.entities.builder.PlantDatabase
import com.sunitcb.gardenmate.repository.PlantRepository
import kotlinx.coroutines.launch

class GardenLogsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PlantRepository
    val allPlants: LiveData<List<Plants>>

    init{
        val plantDao = PlantDatabase.getDatabase(application).plantDao()
        repository = PlantRepository(plantDao)
        allPlants = repository.allPlants
    }

    fun deletePlant(plant: Plants) {
        viewModelScope.launch {
            try{
                val result = repository.delete(plant)
            } catch (ex: Exception){
                Log.e("GardenLogsViewModel", "Error deleting plant.")
            }
        }
    }
}