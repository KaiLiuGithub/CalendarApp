package com.kai.liu.calendar.view.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_create_memo.*

class CreateMemoFragment: CalendarBaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.kai.liu.calendar.R.layout.fragment_create_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        saveButton.setOnClickListener({
            calendarViewModel.addMemo(
                    memoTitle.text.toString(),
                    memoContent.text.toString(),
                    memoTime.text.toString(),
                    memoLocation.text.toString())
            navController.navigate(com.kai.liu.calendar.R.id.action_createMemoFragment_to_calendarFragment)
        })

        cancelButton.setOnClickListener({
            navController.navigate(com.kai.liu.calendar.R.id.action_createMemoFragment_to_calendarFragment)
        })
    }
}