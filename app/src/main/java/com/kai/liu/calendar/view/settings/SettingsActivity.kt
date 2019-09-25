package com.kai.liu.calendar.view.settings

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kai.liu.calendar.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity: AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        navController = Navigation.findNavController(this, R.id.settings_host_fragment)

        backButton.setOnClickListener({
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
        })
    }

    fun setTitle(title: String) {
        toolbarTitle.text = title
    }
}