package com.example.knowledgebase.widget

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.knowledgebase.R
import com.example.knowledgebase.widget.models.BasicItem
import com.example.knowledgebase.widget.utils.image.SetImageAsync

class WidgetListAdapter(private val dataSet: List<BasicItem>) :
    RecyclerView.Adapter<WidgetListAdapter.ViewHolder>() {

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.widget_list_item, parent, false)

        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        if (item.image.isNotBlank()) {
            SetImageAsync.set(holder.containerView.findViewById(R.id.widget_list_header_image), item.image)
        }

        holder.containerView.findViewById<TextView>(R.id.widget_list_item_title).text = item.title
        holder.containerView.findViewById<TextView>(R.id.widget_list_item_subtitle).text = item.subtitle

        if (item.tagImages.isNotEmpty()) {
            SetImageAsync.set(holder.containerView.findViewById(R.id.widget_list_item_flag), item.tagImages[0])
        }

        if (position % 2 == 1) {
            holder.containerView.setBackgroundColor(Color.parseColor("#FF79B9EC"))
        } else {
            holder.containerView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}