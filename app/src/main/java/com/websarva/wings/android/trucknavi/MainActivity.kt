package com.websarva.wings.android.trucknavi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    仮ログイン
    fun onButtonClick(view: View){
        val intent = Intent(applicationContext,navi_setting::class.java)
        startActivity(intent)
    }


}