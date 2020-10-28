package com.example.knowledgebase.widget

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.knowledgebase.R
import com.example.knowledgebase.widget.models.BasicItem
import com.example.knowledgebase.widget.models.BasicItemProperty
import com.example.knowledgebase.widget.utils.image.SetImageAsync

class WidgetListAdapter(private val dataSet: List<BasicItem>) :
    RecyclerView.Adapter<WidgetListAdapter.ViewHolder>() {

    class ViewHolder(
        val containerView: View,
        val parentView: ViewGroup,
    ) : RecyclerView.ViewHolder(containerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val textView = layoutInflater.inflate(R.layout.widget_list_item, parent, false)

        return ViewHolder(textView, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        if (item.image.isNotBlank()) {
            SetImageAsync.set(holder.containerView.findViewById(R.id.widget_list_header_image), item.image)
        }

        holder.containerView.findViewById<TextView>(R.id.widget_list_item_title).text = item.title
        holder.containerView.findViewById<TextView>(R.id.widget_list_item_subtitle).text = item.subtitle

        if (item.tagImages.isNotEmpty()) {
            SetImageAsync.set(holder.containerView.findViewById(R.id.widget_list_item_tag), item.tagImages[0])
        }

        val body = holder.containerView.findViewById<LinearLayout>(R.id.widget_list_item_body)
        if (item.properties.isNotEmpty()) {
            body.visibility = View.VISIBLE
            body.removeAllViews()

            val bodyItems: Array<View> = createBodyViews(item.properties, holder.parentView)
            bodyItems.forEach {
                body.addView(it)
            }
        } else {
            body.visibility = View.GONE
        }

        if (position % 2 == 1) {
            holder.containerView.setBackgroundColor(Color.parseColor("#FF79B9EC"))
        } else {
            holder.containerView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    private fun createBodyViews(properties: Array<BasicItemProperty>, parentView: ViewGroup): Array<View> {
        val layoutInflater = LayoutInflater.from(parentView.context)

        return Array(properties.size) {
            val propertyView = layoutInflater.inflate(R.layout.widget_list_item_body_item, parentView, false)

            val label = "${properties[it].label.capitalize()}: "
            propertyView.findViewById<TextView>(R.id.widget_list_item_body_item_label).text = label
            propertyView.findViewById<TextView>(R.id.widget_list_item_body_item_value).text = properties[it].value

            propertyView
        }
    }

}