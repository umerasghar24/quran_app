package com.example.quran_app.controller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() { //to show which activity is running at that time like oncreate onpause etx
    val Tag ="Lifecycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(Tag,"${javaClass.simpleName} OnCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.d(Tag,"${javaClass.simpleName} OnResume")
        super.onStart()
    }

    override fun onResume() {
        Log.d(Tag,"${javaClass.simpleName} OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(Tag,"${javaClass.simpleName} OnPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d(Tag,"${javaClass.simpleName} OnRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d(Tag,"${javaClass.simpleName} OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(Tag,"${javaClass.simpleName} OnDestroy")
        super.onDestroy()
    }
}