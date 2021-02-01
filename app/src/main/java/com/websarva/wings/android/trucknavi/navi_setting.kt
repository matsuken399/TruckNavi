package com.websarva.wings.android.trucknavi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class navi_setting : AppCompatActivity() {

    //        スピナー選択肢
    val dateList = arrayOf(
        "1A",
        "2A",
        "3A",
        "4A",
        "5A",
    )

    val courseList = arrayOf(
        "50",
        "51",
        "52",
        "53",
        "54",
        "55",
        "56",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi_setting)


        //        スピナー:曜日セット
        val date_select: Spinner = findViewById(R.id.date_in)
        val adapterDate = ArrayAdapter(
            applicationContext, R.layout.spinner_item, dateList
        )
        adapterDate.setDropDownViewResource(R.layout.spinner_dropdown_item)

        date_select.adapter = adapterDate
        date_select.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val dateSet = spinnerParent.selectedItem as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


//        スピナー:コースセット
        val course_select: Spinner = findViewById(R.id.course_in)
        val adapterCourse = ArrayAdapter(
            applicationContext, R.layout.spinner_item, courseList
        )
        adapterCourse.setDropDownViewResource(R.layout.spinner_dropdown_item)

        course_select.adapter = adapterCourse
        course_select.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val courseSet = spinnerParent.selectedItem as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        //    仮マップ表示
        val MapClick = findViewById<Button>(R.id.btMap)

        MapClick.setOnClickListener {
            val intent = Intent(this, map::class.java)
            startActivity(intent)
        }

        //    コース修正画面表示
        val FixClick = findViewById<Button>(R.id.btCourseFix)

        FixClick.setOnClickListener {
            val intent = Intent(this, course_fix::class.java)
            startActivity(intent)
        }
    }
}
