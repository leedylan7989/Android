package com.example.heonlee_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveRecord(view : View){
        val name = playerName.text.toString()

        var position : String = ""

        val goals = goalsNum.text.toString()

        when {
            radioGoalie.isChecked -> {
                position = "Goalie"
            }
            radioDefence.isChecked -> {
                position = "Defence"
            }
            radioForward.isChecked -> {
                position = "Forward"
            }
        }

        val dbHandler : DatabaseHandler = DatabaseHandler(this)

        if(goals.trim() == ""){
            //If the goals field is empty
            Toast.makeText(applicationContext, "Please type the Goals",
                Toast.LENGTH_LONG).show()
        } else if(name.trim() == ""){
            //If the player name field is empty
            Toast.makeText(applicationContext, "Please type the player name",
                Toast.LENGTH_LONG).show()
        } else if(goals.toIntOrNull() == null){
            //If the goals field is not numeric
            Toast.makeText(applicationContext, "Goals must be numeric",
                Toast.LENGTH_LONG).show()
        } else if(goals.toInt() < 0){
            //If the goals field is not positive
            Toast.makeText(applicationContext, "Goals must be positive",
                Toast.LENGTH_LONG).show()
        } else if(position == ""){
            //If the player position is empty
            Toast.makeText(applicationContext, "Please select the player position",
                Toast.LENGTH_LONG).show()
        } else {

            val status = dbHandler.addPlayer(PlayerModelClass(name, position, goals.toInt()))

            if(status > -1){
                Toast.makeText(applicationContext, "Player (ID: $status) saved",
                    Toast.LENGTH_LONG).show()

                playerName.text.clear()
                goalsNum.text.clear()

                when {
                    radioGoalie.isChecked -> {
                        radioGoalie.isChecked = false
                    }
                    radioDefence.isChecked -> {
                        radioDefence.isChecked = false
                    }
                    radioForward.isChecked -> {
                        radioForward.isChecked = false
                    }
                }

            }

        }
    }

    //Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.aboutMenu -> {
                val builder = AlertDialog.Builder(this@MainActivity)

                builder.setTitle("About")

                builder.setMessage("Assignment 2 - Heon Lee, Lin Cui")

                builder.setNeutralButton("Ok"){_,_ ->
                    Toast.makeText(applicationContext, "Thank you", Toast.LENGTH_SHORT).show()
                }

                val dialog : AlertDialog = builder.create()

                dialog.show()


                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun viewRecord(view : View){
        val intent = Intent(this@MainActivity, PlayerListActivity::class.java)

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }




}
