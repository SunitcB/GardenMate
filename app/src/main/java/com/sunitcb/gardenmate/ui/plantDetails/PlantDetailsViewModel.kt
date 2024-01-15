package com.sunitcb.gardenmate.ui.plantDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.entities.builder.PlantDatabase
import com.sunitcb.gardenmate.repository.PlantRepository
import kotlinx.coroutines.launch

class PlantDetailsViewModel(application: Application) :AndroidViewModel(application)  {
    private val repository: PlantRepository
    val allPlants: LiveData<List<Plants>>
    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }

    init{
        val plantDao = PlantDatabase.getDatabase(application).plantDao()
        repository = PlantRepository(plantDao)
        allPlants = repository.allPlants
    }

    fun getPlantById(plantId: Long): LiveData<Plants>{
        return repository.getPlantById(plantId)
    }
}