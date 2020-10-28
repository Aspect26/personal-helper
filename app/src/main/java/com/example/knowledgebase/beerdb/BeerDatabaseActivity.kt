package com.example.knowledgebase.beerdb

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
import com.example.knowledgebase.beerdb.models.Beer
import com.example.knowledgebase.beerdb.models.Country
import com.example.knowledgebase.widget.WidgetListAdapter

class BeerDatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_database)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        findViewById<EditText>(R.id.beerdb_search_input).addTextChangedListener {
            refreshItems(it.toString())
        }

        this.refreshItems()
    }

    private fun refreshItems(filter: String = "") {
        var data = getData()

        if (filter.isNotEmpty()) {
            data = data.filter { it.title.contains(filter) || it.subtitle.contains(filter) }
        }

        val viewManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.beer_db_list).apply {
            layoutManager = viewManager
            adapter = BeerListAdapter(data)
        }

        Log.e("beerdb_log", data.size.toString())
    }

    private fun getData(): List<Beer> =
        listOf(
            Beer("Kutnohorská", "Zlatá 12", 12, 4.9f, 500, 2020, Country.CZE),
            Beer("IKEA", "ÖL LJUS LAGER", null, 4.7f, 330, 2020, Country.NONE),
            Beer("LindemanS", "Framboise lambic beer", null, 2.5f, 250, 2020, Country.BEL),
            Beer("Bohemia regent", "Světlé výčepní", null, 3.9f, 500, 2020, Country.CZE),
            Beer("St. Louis", "Premium Kriek Lambic", null, 3.2f, 250, 2020, Country.BEL),
            Beer("Budvar", "Ležák", null, 4.6f, 500, 2020, Country.CZE),
            Beer("Svijany", "Svijanský máz", 11, 4.8f, 500, 2020, Country.CZE),
        )
}