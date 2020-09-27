package com.example.heonlee_assignment2

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PlayerListAdapter(private val context : Activity, private val name : Array<String>,
                        private val playerPosition : Array<String>, private val goals : Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, name) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val positionText = rowView.findViewById(R.id.textViewPosition) as TextView
        val goalsText = rowView.findViewById(R.id.textViewGoals) as TextView

        nameText.text = "${name[position]}"
        positionText.text = "${playerPosition[position]}"
        goalsText.text = "Goals: ${goals[position]}"

        return rowView
    }

}