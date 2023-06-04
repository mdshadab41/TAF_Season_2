package com.example.taf_season_2

import android.content.Context
import android.content.SharedPreferences
import com.squareup.picasso.BuildConfig

object SharedPrefUtil {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {

        sharedPreferences = context.getSharedPreferences(
            "${BuildConfig.VERSION_CODE}", Context.MODE_PRIVATE
        )

    }

    fun clear(){
        sharedPreferences.edit().clear().apply()
    }

    fun setSoccerTileFavourite(id: String, value: Boolean) {
        setBoolean(id, value)
    }

    fun getSoccerTileFavorite(id: String): Boolean {
        return getBoolean(id)
    }


    private fun setBoolean(name: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(name, value).apply()
    }

    private fun getBoolean(name: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(name, defaultValue)
    }
}