package com.example.knowledgebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.knowledgebase.widget.WidgetActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBeerClick(view: View) {
        val intent = Intent(this, WidgetActivity::class.java)
        this.startActivity(intent)
    }

}