package com.sunitcb.gardenmate.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.entities.builder.PlantDatabase
import com.sunitcb.gardenmate.entities.dao.PlantDao

class PlantRepository(plantDao: PlantDao) {
    private lateinit var plantInitDao: PlantDao
    val allPlants: LiveData<List<Plants>>

    init {
        plantInitDao = plantDao
        allPlants = plantDao.getAllPlants()
    }

    suspend fun insert(plant: Plants){
        plantInitDao.insert(plant)
    }

    suspend fun update(plant: Plants){
        plantInitDao.update(plant)
    }

    suspend fun delete(plant: Plants){
        plantInitDao.delete(plant.id)
    }

    fun getPlantById(plantId: Long): LiveData<Plants>{
        return plantInitDao.getPlantById(plantId)
    }
}