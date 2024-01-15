package com.sunitcb.gardenmate.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Plants(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val description: String,
    val type: String,
    val wateringFrequency: Int,
    val plantedDate: String
)
