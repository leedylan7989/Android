/*
    Heon Lee
    991280638

    Final Project
    2020-04-17

 */
package com.example.heonlee_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_newsdetailview.*

class NewsDetailViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsdetailview)

        var bundle : Bundle ?= intent.extras


        val title = findViewById<TextView>(R.id.textviewTitle)
        val author = findViewById<TextView>(R.id.author)
        val source = findViewById<TextView>(R.id.source)
        val description = findViewById<TextView>(R.id.description)


        val titleStr : String? = bundle!!.getString("title")
        val authorStr : String? = bundle!!.getString("author")
        val sourceStr : String? = bundle!!.getString("source")
        val descriptionStr : String? = bundle!!.getString("description")


        title.text = titleStr
        author.text = authorStr
        source.text = sourceStr
        description.text = descriptionStr

        returnBtn.setOnClickListener {

            val intent = Intent(this@NewsDetailViewActivity, MainActivity::class.java)

            startActivity(intent)
        }

    }
}
