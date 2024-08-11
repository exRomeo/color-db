package com.example.roomdbdemo.data.localsource.room_db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdbdemo.data.localsource.room_db.dao.ColorDao
import com.example.roomdbdemo.data.localsource.room_db.entity.ColorEntity


@Database(entities = [ColorEntity::class], version = 1)
abstract class ColorDataBase : RoomDatabase() {

    abstract val dao: ColorDao


    companion object {
        @Volatile
        private var INSTANCE: ColorDataBase? = null

        fun getInstance(context: Context): ColorDataBase {
            return INSTANCE ?: synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context = context,
                    ColorDataBase::class.java,
                    "ColorDatabase"
                )
                    .build()
                    .also { INSTANCE = it }

            }
        }
    }
}