package com.kai.liu.calendar.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kai.liu.calendar.view.settings.SettingsBaseFragment

class NotificationFragment: SettingsBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        settingsViewModel.init()
        return inflater.inflate(com.kai.liu.calendar.R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        (getActivity() as SettingsActivity).setTitle("Notification Settings")
    }
}