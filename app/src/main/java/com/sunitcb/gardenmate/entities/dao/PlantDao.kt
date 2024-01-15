package com.sunitcb.gardenmate.entities.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sunitcb.gardenmate.entities.Plants

@Dao
interface PlantDao {
    @Query("Select * from plants")
    fun getAllPlants(): LiveData<List<Plants>>

    @Insert
    suspend fun insert(plants: Plants)

    @Update
    suspend fun update(plants: Plants)

    @Query("Delete from plants where id = :plantId")
    suspend fun delete(plantId: Long)

    @Query("Select * from plants where id = :plantId")
    fun getPlantById(plantId: Long) : LiveData<Plants>
}