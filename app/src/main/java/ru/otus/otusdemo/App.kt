package ru.otus.otusdemo

import android.app.Application
import android.util.Log

private const val TAG = "App"

class App : Application() {

    val id = TAG

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG ,"call onCreate")
    }

    override fun onTerminate() {
        Log.d(TAG ,"call onTerminate")
        super.onTerminate()
    }
}