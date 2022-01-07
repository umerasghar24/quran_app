package com.example.quran_app.controller

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
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
    fun openSoftKeyboard() {
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}