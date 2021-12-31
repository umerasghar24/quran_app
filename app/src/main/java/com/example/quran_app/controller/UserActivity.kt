package com.example.quran_app.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.quran_app.R

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val uToolBar:Toolbar=findViewById(R.id.toolbar_user)
        setSupportActionBar(uToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        uToolBar.setNavigationOnClickListener { view->onBackPressed() }
        supportActionBar?.setTitle("Settings")
        supportActionBar?.themedContext

    }
}