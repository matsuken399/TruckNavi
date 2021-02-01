package com.websarva.wings.android.trucknavi

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coursefix.*
import kotlinx.android.synthetic.main.activity_navi_setting.*

class course_fix : AppCompatActivity() {

    lateinit var usersDBHelper: UsersDBHelper

    //        スピナー選択肢
//    val dateList = arrayOf(
//            "1A",
//            "2A",
//            "3A",
//            "4A",
//            "5A",
//    )

//    val courseList = arrayOf(
//            "50",
//            "51",
//            "52",
//            "53",
//            "54",
//            "55",
//            "56",
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coursefix)

//        スピナー曜日セット
//        val date_select: Spinner = findViewById(R.id.sp_date)
//
//        val date_adapter = ArrayAdapter(
//                applicationContext, R.layout.spinner_item, dateList
//        )
//        date_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
//
//        date_select.adapter = date_adapter
//        date_select.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }

//        スピナーコースセット

//        val course_select: Spinner = findViewById(R.id.sp_course)
//
//        val course_adapter = ArrayAdapter(
//                applicationContext, android.R.layout.simple_spinner_item, courseList
//        )
//        course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        course_select.adapter = course_adapter
//        course_select.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }


        //        戻るボタン
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        DBヘルパー設定
        usersDBHelper = UsersDBHelper(this)

    }

        fun addUser(v: View) {

            val editDate = findViewById<EditText>(R.id.et_date)
            val date = editDate.text.toString()
//            val date = dateList[date_select.selectItemPosition].text.toString()
            val editCourse = findViewById<EditText>(R.id.et_course)
            val course = editCourse.text.toString()
//            val course = courseList[course_select.selectItemPosition].text.toString()
            val editNo = findViewById<EditText>(R.id.et_No)
            val No = editNo.text.toString()
            val editName = findViewById<EditText>(R.id.et_name)
            val name = editName.text.toString()
            val editLatLng = findViewById<EditText>(R.id.et_latlng)
            val latlng = editLatLng.text.toString()

            val result = usersDBHelper.insertUser(UserModel(date = date, course = course, No = No, name = name, latlng = latlng))

            this.et_date.setText("")
            this.et_course.setText("")
            this.et_No.setText("")
            this.et_name.setText("")
            this.et_latlng.setText("")
            this.tv_result.text = "Added user : " + result
            this.ll_entries.removeAllViews()
        }

        fun deleteUser(v: View) {
            val userId = this.et_No.text.toString()
            val result = usersDBHelper.deleteUser(userId)
            this.tv_result.text = "Deleted user : " + result
            this.ll_entries.removeAllViews()
        }

        fun showAllUsers(v: View) {
            var users = usersDBHelper.readAllUsers()
            this.ll_entries.removeAllViews()
            users.forEach {
                var tv_user = TextView(this)
                tv_user.textSize = 30F
                tv_user.text = it.date.toString() + " - " + it.course.toString() + " - " + it.No.toString() + " - " + it.name.toString() + " - " + it.latlng.toString()

                this.ll_entries.addView(tv_user)
            }
            this.tv_result.text = "Fetched " + users.size + " users"
        }


        //    セッティング画面戻り
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == android.R.id.home) {
                finish()
            }
            return super.onOptionsItemSelected(item)
        }
    }
