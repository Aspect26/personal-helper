package com.example.knowledgebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.knowledgebase.db.Database
import com.example.knowledgebase.db.hardcoded.HardcodedDatabase
import com.example.knowledgebase.widget.WidgetActivity

class MainActivity : AppCompatActivity() {

    private val database: Database = HardcodedDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBeerClick(view: View) {
        val widgetSpecification = database.getWidgetSpecification("beerDb")

        val intent = Intent(this, WidgetActivity::class.java)
        intent.putExtra(WidgetActivity.INTENT_DATA_SPECIFICATION, widgetSpecification)

        this.startActivity(intent)
    }

}