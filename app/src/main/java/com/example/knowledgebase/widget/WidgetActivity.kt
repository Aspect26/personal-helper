package com.example.knowledgebase.widget

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.knowledgebase.R
import com.example.knowledgebase.db.models.BasicItem
import com.example.knowledgebase.widget.specification.SpecificationEncoder
import com.example.knowledgebase.widget.specification.models.WidgetSpecification

class WidgetActivity : AppCompatActivity() {

    lateinit var widgetSpecification: WidgetSpecification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.widget_fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        findViewById<EditText>(R.id.widget_search_input).addTextChangedListener {
            refreshItems(it.toString())
        }

        val specificationString = intent.getStringExtra(INTENT_DATA_SPECIFICATION)
        if (specificationString != null) {
            this.widgetSpecification = SpecificationEncoder.encode(specificationString)
            this.refreshItems()
        } else {
            Log.e("widget_log", "No specification passed to the widget activity")
        }
    }

    private fun refreshItems(filter: String = "") {
        val data: List<BasicItem> = if (filter.isEmpty()) {
            widgetSpecification.widgetData.asList()
        } else {
            widgetSpecification.widgetData.filter { it.title.contains(filter) || it.subtitle.contains(filter) }
        }

        val viewManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.widget_list).apply {
            layoutManager = viewManager
            adapter = WidgetListAdapter(data)
        }

        Log.e("widget_log", data.size.toString())
    }

    companion object {
        const val INTENT_DATA_SPECIFICATION = "INTENT_DATA_SPECIFICATION"
    }
}