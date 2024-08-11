package com.example.roomdbdemo.data.localsource.room_db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdbdemo.data.localsource.room_db.entity.ColorEntity

@Dao
interface ColorDao {

    @Query("select * from color")
    fun getColors(): LiveData<List<ColorEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertColor(vararg color: ColorEntity)
}