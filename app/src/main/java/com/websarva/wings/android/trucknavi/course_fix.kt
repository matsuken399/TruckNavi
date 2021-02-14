package com.websarva.wings.android.trucknavi

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_coursefix.*
import kotlinx.android.synthetic.main.activity_navi_setting.*

class course_fix : AppCompatActivity() {

    companion object{
        lateinit var dbHandler: DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coursefix)

        dbHandler = DBHandler(this,null,null,1)

        viewCustomers()

        fab.setOnClickListener{
            val i = Intent(this,AddCustomerActivity::class.java)
            startActivity(i)
        }
    }

    private fun viewCustomers(){
        val customersList = dbHandler.getCustomers(this)
        val adapter = CustomerAdapter(this,customersList)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = adapter
    }

    override fun onResume(){
        viewCustomers()
        super.onResume()
    }

        //    セッティング画面戻り
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == android.R.id.home) {
                finish()
            }
            return super.onOptionsItemSelected(item)
        }
    }