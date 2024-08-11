package com.example.roomdbdemo

import android.graphics.Color

data class ColorModel(val id: Int, val name: String, val r: Int, val g: Int, val b: Int) {
    fun getColor(): Int {
        return Color.rgb(r, g, b)
    }



}
