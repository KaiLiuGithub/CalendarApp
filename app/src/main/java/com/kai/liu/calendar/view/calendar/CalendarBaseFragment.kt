package com.kai.liu.calendar.view.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.kai.liu.calendar.viewmodel.CalendarViewModel

abstract class CalendarBaseFragment: Fragment() {
    lateinit var calendarViewModel: CalendarViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        calendarViewModel = ViewModelProviders.of(activity as CalendarActivity).get(CalendarViewModel::class.java)
    }
}