package com.example.roomdbdemo.data.localsource.room_db.entity

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "color")
class ColorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val r: Int,
    val g: Int,
    val b: Int
)