package com.kai.liu.calendar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.generic_action_bar.view.*

class GenericActionBarView(context: Context, attrs: AttributeSet): CardView(context, attrs) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.generic_action_bar, this, true)

        view.drawerMenuButton.setOnClickListener({
            genericDrawerLayout?.let {
                if (genericDrawerLayout.isDrawerOpen(genericDrawerLayout)) {
                    genericDrawerLayout.closeDrawer(genericDrawerLayout)
                } else {
                    genericDrawerLayout.openDrawer(genericDrawerLayout)
                }
            }
        })

        view.extraMenuButton.setOnClickListener({

        })
    }
}