package com.sunitcb.gardenmate.ui.addPlant

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

class AddPlantViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PlantRepository

    init{
        val plantDao = PlantDatabase.getDatabase(application).plantDao()
        repository = PlantRepository(plantDao)
    }

    fun insert(plant: Plants) = viewModelScope.launch {
        repository.insert(plant)
    }
}