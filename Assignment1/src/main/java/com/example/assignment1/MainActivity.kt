package com.example.assignment1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText
        val amount = findViewById(R.id.amountvalue) as EditText

        //Buttons
        val calculate = findViewById(R.id.calculate) as Button
        val clear = findViewById(R.id.clear) as Button

        //Spinner
        val spinner = findViewById(R.id.spinner) as Spinner
        val numSpinner = findViewById(R.id.numSpinner) as Spinner

        //Results
        val lbTip = findViewById(R.id.tipLabel) as TextView
        val tfTip = findViewById(R.id.tipResult) as TextView

        val lbTotal = findViewById(R.id.totalLabel) as TextView
        val tftotal = findViewById(R.id.total) as TextView

        val lbSplit = findViewById(R.id.splitLabel) as TextView
        val tfSplit = findViewById(R.id.tfSplit) as TextView

        val tipvalue = findViewById(R.id.tipvalue) as EditText


        val rates = arrayOf("10", "15", "20", "Other")
        val numPeople = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")


        //Tip Spinner
        if(spinner != null){
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rates)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = spinnerAdapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(spinner.selectedItem.toString() == "Other")
                    tipvalue.isEnabled = true
                else {
                    tipvalue.text.clear()
                    tipvalue.isEnabled = false
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                tipvalue.isEnabled = false
            }
        }

        //Number of people spinner
        if(numSpinner != null){
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                numPeople)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            numSpinner.adapter = spinnerAdapter
        }

        //Clear
        clear.setOnClickListener{
            //Hide all result labels
            lbTip.isVisible = false
            tfTip.isVisible = false
            lbTotal.isVisible = false
            tftotal.isVisible = false
            lbSplit.isVisible = false
            tfSplit.isVisible = false

            //Clear input fields
            amount.text.clear()
            tipvalue.text.clear()

            //Set number of people spinner value to "1"
            numSpinner.setSelection(0)

            //Set tip spinner value to "10"
            spinner.setSelection(0)


        }

        //Calculate button click
        calculate.setOnClickListener{


            if(amount.text.toString() == ""){ //empty amount field
                val makeToast = Toast.makeText(getApplicationContext(), "Please type the amount",
                    Toast.LENGTH_SHORT)
                makeToast.show()
            } else if(spinner.selectedItem.toString() == "Other" && tipvalue.text.toString() == ""){
                //empty tip field when "Other" is selected
                val makeToast = Toast.makeText(getApplicationContext(), "Please type the tip" +
                        " percent",
                    Toast.LENGTH_SHORT)
                makeToast.show()
            } else if(amount.text.toString().toDoubleOrNull() == null){
                //Checks for non double characters
                val makeToast = Toast.makeText(getApplicationContext(), "Please type a number" +
                        "(double)",
                    Toast.LENGTH_SHORT)
                makeToast.show()
            }else if(spinner.selectedItem.toString() == "Other"
                && tipvalue.text.toString().toDoubleOrNull() == null){
                //Checks for non double characters
                val makeToast = Toast.makeText(getApplicationContext(), "Please type " +
                        "a number",
                    Toast.LENGTH_SHORT)
                makeToast.show()
            } else if(amount.text.toString().toDouble() <= 0.0 ||
                (spinner.selectedItem.toString() == "Other" &&
                        tipvalue.text.toString().toDouble() <= 0.0)){
                //Check for negative value
                val makeToast = Toast.makeText(getApplicationContext(), "Please type " +
                        "a positive non-zero number",
                    Toast.LENGTH_SHORT)
                makeToast.show()
            }
            else {
                //Visible result section
                lbTip.isVisible = true
                tfTip.isVisible = true
                lbTotal.isVisible = true
                tftotal.isVisible = true

                //Tip percent
                var tipPercent : Double = 0.0

                if(spinner.selectedItem.toString() == "Other"){
                    tipPercent = tipvalue.text.toString().toDouble()
                } else {
                    tipPercent = spinner.selectedItem.toString().toDouble()
                }

                //Calculate Tip
                val tip = String.format("%.2f", amount.text.toString().toDouble()/100).toDouble() *
                        tipPercent

                //Display tip
                tfTip.text = "$" + tip

                //Calculate Total
                val total = String.format("%.2f", amount.text.toString().toDouble()).toDouble() +
                        tip

                //Display total
                tftotal.text = "$" + total

                if(numSpinner.selectedItem.toString().toDouble() > 1.0){
                    lbSplit.isVisible = true
                    tfSplit.isVisible = true

                    val num = numSpinner.selectedItem.toString().toDouble()

                    tfSplit.text = "$" + String.format("%.2f", (total/num)).toDouble()
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }
}
