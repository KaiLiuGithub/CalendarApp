package com.kai.liu.calendar.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment: SettingsBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        settingsViewModel.init()
        return inflater.inflate(com.kai.liu.calendar.R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        (getActivity() as SettingsActivity).setTitle("Settings")

        notificationLayout.setOnClickListener({
            navController.navigate(com.kai.liu.calendar.R.id.action_settingsFragment_to_notficiationFragment)
        })
    }
}