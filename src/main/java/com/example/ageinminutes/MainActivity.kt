package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate: TextView? = null
    private var outputText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.selectbtn)
        selectedDate = findViewById(R.id.selectedDate)
        outputText = findViewById(R.id.finalOut)
        btn.setOnClickListener {view->
            clickDatePicker()

        }
    }
    fun clickDatePicker(){
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view, selectedyear, selectedmonth, dayOfMonth->
            val mydate = "$dayOfMonth/${selectedmonth+1}/$selectedyear"
        selectedDate?.text = mydate
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val thedate = sdf.parse(mydate)
            val dateinmin = thedate.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val curdateinmin = currentDate.time/60000
            val diffr = curdateinmin - dateinmin
            outputText?.text = diffr.toString()
        },
            year,
            month,
            day

            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}