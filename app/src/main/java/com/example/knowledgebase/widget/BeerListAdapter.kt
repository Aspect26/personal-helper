package com.example.knowledgebase.beerdb

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.knowledgebase.R
import com.example.knowledgebase.beerdb.models.Beer
import com.example.knowledgebase.beerdb.utils.CountryToFlagResource

class BeerListAdapter(private val dataSet: List<Beer>) :
    RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.beer_list_item, parent, false)

        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = dataSet[position]

        holder.containerView.findViewById<TextView>(R.id.beerdb_list_item_title).text = beer.title
        holder.containerView.findViewById<TextView>(R.id.beerdb_list_item_subtitle).text = beer.subtitle

        val countryFlagResource = CountryToFlagResource.get(beer.country)
        holder.containerView.findViewById<ImageView>(R.id.beerdb_list_item_flag).setImageResource(countryFlagResource)

        if (beer.degrees == null) {
            holder.containerView.findViewById<View>(R.id.beerdb_list_item_degrees).visibility = View.GONE
        } else {
            holder.containerView.findViewById<View>(R.id.beerdb_list_item_degrees).visibility = View.VISIBLE
            holder.containerView.findViewById<TextView>(R.id.beerdb_list_item_degrees_value).text = "${beer.degrees}°"
        }

        holder.containerView.findViewById<TextView>(R.id.beerdb_list_item_percentage_value).text = "${beer.percentage}%"
        holder.containerView.findViewById<TextView>(R.id.beerdb_list_item_year_value).text = "${beer.year}"

        if (position % 2 == 1) {
            holder.containerView.setBackgroundColor(Color.parseColor("#FF79B9EC"))
        } else {
            holder.containerView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}