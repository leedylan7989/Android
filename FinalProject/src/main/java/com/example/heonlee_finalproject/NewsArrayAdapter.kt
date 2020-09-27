/*
    Heon Lee
    991280638

    Final Project
    2020-04-17

 */


package com.example.heonlee_finalproject

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class NewsArrayAdapter(private val context : Activity, private val news : ArrayList<NewsItem>)
    : ArrayAdapter<NewsItem>(context, R.layout.custom_list, news) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val newsTitle = rowView.findViewById<TextView>(R.id.title)

        newsTitle.text = "${news[position].title}"

        return rowView
    }
}