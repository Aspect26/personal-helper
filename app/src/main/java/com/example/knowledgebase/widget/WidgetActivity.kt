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
import com.example.knowledgebase.widget.models.BasicItem

class WidgetActivity : AppCompatActivity() {

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

        this.refreshItems()
    }

    private fun refreshItems(filter: String = "") {
        var data = getData()

        if (filter.isNotEmpty()) {
            data = data.filter { it.title.contains(filter) || it.subtitle.contains(filter) }
        }

        val viewManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.widget_list).apply {
            layoutManager = viewManager
            adapter = WidgetListAdapter(data)
        }

        Log.e("widget_log", data.size.toString())
    }

    private fun getData(): List<BasicItem> =
        listOf(
            BasicItem("Kutnohorská", "Zlatá 12", "base64:/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAQDAwQDAwQEAwQFBAQFBgoHBgYGBg0JCggKDw0QEA8NDw4RExgUERIXEg4PFRwVFxkZGxsbEBQdHx0aHxgaGxr/2wBDAQQFBQYFBgwHBwwaEQ8RGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhoaGhr/wAARCABEAIADASIAAhEBAxEB/8QAGgABAQEBAQEBAAAAAAAAAAAABAUGAAMIB//EACsQAAEDAwIEBQUBAAAAAAAAAAADBEECBREG0gcSE0IBcaOk0TFjgoOEgf/EABkBAQEAAwEAAAAAAAAAAAAAAAYEBwgJA//EACMRAAIBAwQDAQEBAAAAAAAAAAACBAEDQQUGMdEycZKT0hH/2gAMAwEAAhEDEQA/AMtQJTDUCUzaRjbibkUmJoDJiaCRgJNyJoFJhaBSZKwFnZEpiUwyYlMkYCTciaBKYagSmSsBJuRSYlMMmJTJWAk7IlMTQGTE0EjASbkSmKTCpikyVgJOyfOlAlMNQJTMksdnJuRSYmgMmJoJGAk3ImgUmFoFJkrAWdkSmJTDJiUyRgJNyJoEphqBKZKwEm5FJiUwyYlMlYCTsiUxNAZMTQSMBJuRKYpMKmKTJWAk7J+eIaWtNWMtPVr+SmhpCzVYyz9Wv5PVtBXbQaUru3cdeZ979H7MtRd57nu+eo36+7r/ANB0NF2OrGWPrKbimhoXT9X1YeupuFtoK7aChd1bhrzOu/o/Ysi7j1u75zLlfbt2AQ4f6cqxm3e4U3FRDhzpmrGbb7hXcUm0FdtBQu59frzNu/o3Ysi6lOu+d5q+2r2SUOGelqvra/cq7imhwu0nVjNq90tvLLaCu2goXcuuV5mXf0bsWRWa751/32Yl1w20wjnp2zl/pV3EN1oyyI56bLl/cpuP0d/JlX8lC7i1qvMu59t2LYsGJd87S190oYV3Yrejnpt+X86vkhO26aOenTy/74muuEmWuE+RQuv6xXmVc+27FkXQdHu+cW3X2i9Gedvl0c9NTl/HwITvUdzRz03PLj7dPwVX8mVf9x7rrmrV5k3PtuxZF2htu756fZr7tp0eDvXN/Rz03/L+hPaQnfE7VSOeldeX+ZLaedwkytwkoXWdTrzJf6bsWRdgbOu+elR6+7Nv+T6NbQV20EhtBXbQYfU5OQsFZtBXbQSG0FdtBWo6hYK7aCu2gkNoK7aCpB3CKzaCu2gkNoK7aCtR1CwEfyZV/Jqn8mVfyVKPIJlrhJlrhPkam4SZa4T5FajuDgyz+TKv+41T+TKv+4qUdwsGXuEmVuEmquEmVuElSjuFg+jW0FdtBxxjZTjHCwVm0FdtBxxWo6hYK7aCu2g44qQdwis2grtoOOK1HULAR/JlX8nHFSjyCZa4SZa4T5HHFajuDgyz+TKv+444qUdwsGXuEmVuEnHFSjuFg//Z", arrayOf()),
            BasicItem("IKEA", "ÖL LJUS LAGER", "url:https://stmedia.stimg.co/ctyp-092320-Beer-Issue-Getty.jpg?w=1200&h=630", arrayOf()),
            BasicItem("LindemanS", "Framboise lambic beer", "url:https://stmedia.stimg.co/ctyp-092320-Beer-Issue-Getty.jpg?w=1200&h=630", arrayOf()),
            BasicItem("Bohemia regent", "Světlé výčepní", "url:https://stmedia.stimg.co/ctyp-092320-Beer-Issue-Getty.jpg?w=1200&h=630", arrayOf()),
            BasicItem("St. Louis", "Premium Kriek Lambic", "url:https://stmedia.stimg.co/ctyp-092320-Beer-Issue-Getty.jpg?w=1200&h=630", arrayOf()),
            BasicItem("Budvar", "Ležák", "url:https://stmedia.stimg.co/ctyp-092320-Beer-Issue-Getty.jpg?w=1200&h=630", arrayOf()),
            BasicItem("Svijany", "Svijanský máz", "url:https://stmedia.stimg.co/ctyp-092320-Beer-Issue-Getty.jpg?w=1200&h=630", arrayOf()),
        )
}