package com.example.mg

import android.app.Application
import android.content.Context
import com.example.mg.data.NoteDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        database = NoteDatabase.getInstance(appContext)
    }



    companion object {
        lateinit var appContext: Context
        lateinit var database: NoteDatabase
    }


}
