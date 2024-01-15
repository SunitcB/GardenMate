package com.sunitcb.gardenmate.entities.builder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.entities.dao.PlantDao

@Database(entities = [Plants::class], version = 1, exportSchema = false)
abstract class PlantDatabase: RoomDatabase() {
    abstract fun  plantDao(): PlantDao

    companion object{
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, PlantDatabase::class.java,"plant_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}