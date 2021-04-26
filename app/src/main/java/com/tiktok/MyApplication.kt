package com.tiktok

import android.app.Application
import android.content.Context

class MyApplication:Application() {
    companion object{
        lateinit var context:Context
        val KEY = "5883ba335ac96a9803460743591da0fe"
    }
    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }
}