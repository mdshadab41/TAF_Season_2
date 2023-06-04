package com.example.taf_season_2

import android.app.Application

class EplApplication: Application() {

    companion object{
        lateinit var application: EplApplication
        lateinit var soccerTileList: ArrayList<SoccerTile>
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        SharedPrefUtil.init(this)
    }

}