package com.kai.liu.calendar.view.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.kai.liu.calendar.viewmodel.SettingsViewModel


abstract class SettingsBaseFragment: Fragment() {
    lateinit var settingsViewModel: SettingsViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsViewModel = ViewModelProviders.of(activity as SettingsActivity).get(SettingsViewModel::class.java)
    }
}