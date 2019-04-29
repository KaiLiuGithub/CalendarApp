package com.kai.liu.calendar.view

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.kai.liu.calendar.R
import kotlinx.android.synthetic.main.generic_menu_layout.view.*

class GenericMenuLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.generic_menu_layout, this, true)

        view.shareMenuButton.setOnClickListener({
            var shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Sent from Liu Kai's Calendar App")

            startActivity(context, Intent.createChooser(shareIntent, "Share via"), null)
        })
    }
}