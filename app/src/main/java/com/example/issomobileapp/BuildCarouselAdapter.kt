package com.example.issomobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BuildCarouselAdapter(private val carouselDataList: List<Int>) :
    RecyclerView.Adapter<BuildCarouselAdapter.CarouselItemViewHolder>() {

    // MARK: Inner Class

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // MARK: Override Functions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.build_expanded_carousel_item, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageView2)
        imageView.setImageResource(carouselDataList[position])
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

}