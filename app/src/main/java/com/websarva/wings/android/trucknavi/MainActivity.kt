package com.websarva.wings.android.trucknavi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 仮ログイン
    fun onButtonClick(view: View) {
        val intent = Intent(applicationContext, NaviSettingActivity::class.java)
        startActivity(intent)
    }


}