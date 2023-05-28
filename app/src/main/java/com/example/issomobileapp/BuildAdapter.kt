package com.example.issomobileapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.issomobileapp.databinding.BuildItemBinding

class BuildAdapter : RecyclerView.Adapter<BuildAdapter.BuildsHolder>() {

    // MARK: Public Properties

    val buildList = ArrayList<Build>()
    var onBuildClick: ((Build) -> Unit)? = null

    // MARK: Inner Class
    class BuildsHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = BuildItemBinding.bind(item)

        fun bind(build: Build) = with(binding) {
            buildImage.setImageResource(build.imageId)
            buildTitle.text = build.title
            buildCity.text = build.city
            buildRooms.text = "Комнат: " + build.roomCount.toString()
            buildSize.text = "Квадратура: " + build.size.toString() + " м²"
            buildPrice.text = "Цена: " + build.price.toString() + " ₽"

        }

    }

    // MARK: Override Functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.build_item, parent, false)
        return BuildsHolder(view)
    }

    override fun onBindViewHolder(holder: BuildsHolder, position: Int) {
        val build = buildList[position]
        holder.bind(buildList[position])
        holder.itemView.setOnClickListener {
            onBuildClick?.invoke(build)
        }
    }

    override fun getItemCount(): Int {
        return buildList.size
    }

    // MARK: Public Functions
    fun addBuild(build: Build) {
        if (!buildList.contains(build)) {
            buildList.add(build)
            notifyDataSetChanged()
        }
    }


    fun removeBuild(build: Build) {
        buildList.remove(build)
        notifyDataSetChanged()
    }


}