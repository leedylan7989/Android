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
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ClassCastException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        val arrayAdapter : ArrayAdapter<*>
        val NewsItemList = ArrayList<NewsItem>()

        val queue = Volley.newRequestQueue(this)
        //The API url with the free API key
        val url : String = "http://newsapi.org/v2/top-headlines?country=ca&category=business&apiKey=468adb8d3c0c485592bf5bbd52e1cdb8"

        val stringReq = StringRequest (
            Request.Method.GET, url, Response.Listener<String>{ response ->
                var strResp = response.toString()

                val jsonObj : JSONObject = JSONObject(strResp)

                val jsonArray : JSONArray = jsonObj.getJSONArray("articles")

                for(i in 0 until jsonArray.length()) {

                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    //Fetch title
                    val title = jsonInner.get("title") as String

                    //Fetch description
                    val description = jsonInner.get("description") as String

                    //Fetch source name
                    //First, get the array
                    //Then, get "name" attribute
                    val source = jsonInner.get("source") as JSONObject
                    val sourceName = source.get("name") as String
                    var author = ""

                    //Fetch author
                    try {
                        author = jsonInner.get("author") as String
                    } catch (e : ClassCastException){ //If author name is null
                        author = "No Author"
                    }



                    val newsItem : NewsItem = NewsItem(title, description, sourceName, author)

                    //Populate the NewsItem ArrayList
                    NewsItemList.add(newsItem)

                }

            },
            Response.ErrorListener {

                //Show Alert Dialog
                val builder = AlertDialog.Builder(this)

                builder.setTitle("Data Load Failed.")

                val msg = "Failed to fetch News Data from the News API"

                builder.setMessage(msg)

                builder.setNeutralButton("Okay") { _, _ ->
                }


                val dialog: AlertDialog = builder.create()

                dialog.show()
            }
        )

        queue.add(stringReq)

        arrayAdapter = NewsArrayAdapter(this, NewsItemList)

        listView.adapter = arrayAdapter


        listView.setOnItemClickListener { parent, view, position, id ->
            //Get the position of the clicked item
            //Convert into Int
            val element = position.toString().toInt()

            val newsItem = NewsItemList[element]

            val intent = Intent(this@MainActivity, NewsDetailViewActivity::class.java )

            intent.putExtra("title", newsItem.title)
            intent.putExtra("author", newsItem.author)
            intent.putExtra("source", newsItem.sourceName)
            intent.putExtra("description", newsItem.description)

            startActivity(intent)

        }
    }
}
