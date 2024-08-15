package com.example.roomdbdemo.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.roomdbdemo.data.localsource.room_db.dao.ColorDao
import com.example.roomdbdemo.data.localsource.room_db.entity.ColorEntity
import com.example.roomdbdemo.presentation.models.ColorModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainActivityViewModel(private val colorDao: ColorDao) : ViewModel() {

    val colorsList: LiveData<List<ColorModel>> by lazy {
        colorDao.getColors().map {
            it.map { colorEntity ->
                ColorModel(
                    colorEntity.id,
                    colorEntity.name,
                    colorEntity.r,
                    colorEntity.g,
                    colorEntity.b
                )
            }
        }
    }

val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    Log.i(TAG, ": ")
}
    fun insertColor(color: ColorModel) {
    val job =     viewModelScope.launch(exceptionHandler) {
            colorDao.insertColor(ColorEntity(0, color.name, color.r, color.g, color.b))
        }

        job.cancel()
    }
}

class MainActivityViewModelFactory(private val colorDao: ColorDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            MainActivityViewModel(colorDao) as T
        else
            throw IllegalArgumentException("ViewModel Not Found")
    }
}