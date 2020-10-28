package com.example.knowledgebase.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.example.knowledgebase.R
import com.example.knowledgebase.db.Database
import com.example.knowledgebase.db.hardcoded.HardcodedDatabase
import com.example.knowledgebase.utils.image.SetImageAsync
import com.example.knowledgebase.widget.WidgetActivity

class LauncherActivity : AppCompatActivity() {

    private val database: Database = HardcodedDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        this.setWidgetButtons()
    }

    private fun setWidgetButtons() {
        val buttonsLayout = findViewById<GridLayout>(R.id.activity_launcher_buttons_grid)
        val widgets = database.getWidgets()

        widgets.forEach { widget ->
            val launcherButton = layoutInflater.inflate(R.layout.launcher_button, null)

            launcherButton.findViewById<ImageButton>(R.id.launcher_button_image).setOnClickListener {
                onLauncherButtonClick(widget.id)
            }
            SetImageAsync.set(
                launcherButton.findViewById<ImageButton>(R.id.launcher_button_image),
                widget.image,
                SetImageAsync.Dimensions(126, 126)
            )

            launcherButton.findViewById<TextView>(R.id.launcher_button_label).text = widget.label

            buttonsLayout.addView(launcherButton)
        }
    }

    private fun onLauncherButtonClick(widgetId: String) {
        val widgetSpecification = database.getWidgetSpecification(widgetId)

        val intent = Intent(this, WidgetActivity::class.java)
        intent.putExtra(WidgetActivity.INTENT_DATA_SPECIFICATION, widgetSpecification)

        this.startActivity(intent)
    }

}