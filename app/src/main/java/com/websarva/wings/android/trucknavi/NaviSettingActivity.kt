package com.websarva.wings.android.trucknavi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class NaviSettingActivity : AppCompatActivity() {

    private var dateSet: String? = null
    private var courseSet: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi_setting)

        // スピナー:曜日セット
        val dateSelect :Spinner = findViewById(R.id.date_in)
        val dateItem = resources.getStringArray(R.array.date_list)
        val dateAdapter = ArrayAdapter(this,R.layout.spinner_dropdown_item,dateItem)
        dateSelect.adapter = dateAdapter

        dateSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val spinner = parent as? Spinner
                val item1 = spinner?.selectedItem as? String
                dateSet = item1
            }
        }

        // スピナー:コースセット
        val courseSelect :Spinner = findViewById(R.id.course_in)
        val courseItem = resources.getStringArray(R.array.course_list)
        val courseAdapter = ArrayAdapter(this,R.layout.spinner_dropdown_item,courseItem)
        courseSelect.adapter = courseAdapter

        courseSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val spinner = parent as? Spinner
                val item2 = spinner?.selectedItem as? String
                courseSet = item2
            }
        }


        // 仮マップ表示
        val MapClick = findViewById<Button>(R.id.btMap)

        MapClick.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        // コース修正画面表示
        val FixClick = findViewById<Button>(R.id.btCourseFix)

        FixClick.setOnClickListener {
            val intent = Intent(this, CourseFixActivity::class.java)

            val dateResult = dateSet
            val courseResult = courseSet

            intent.putExtra("date", dateResult)
            intent.putExtra("course", courseResult)

            startActivity(intent)
        }
    }
}
