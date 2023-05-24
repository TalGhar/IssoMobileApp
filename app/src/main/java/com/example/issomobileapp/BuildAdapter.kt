package com.example.issomobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.issomobileapp.databinding.BuildItemBinding

class BuildAdapter : RecyclerView.Adapter<BuildAdapter.BuildsHolder>() {

    val buildList = ArrayList<Build>()

    class BuildsHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = BuildItemBinding.bind(item)

        fun bind(build: Build) = with(binding) {
            buildImage.setImageResource(build.imageId)
            buildTitle.text = build.title
            buildRooms.text = "Комнат: " + build.roomCount.toString()
            buildSize.text = "Квадратура: " + build.size.toString() + " м²"
            buildPrice.text = "Цена: " + build.price.toString() + " ₽"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.build_item, parent, false)
        return BuildsHolder(view)
    }

    override fun onBindViewHolder(holder: BuildsHolder, position: Int) {
        holder.bind(buildList[position])
    }

    override fun getItemCount(): Int {
        return buildList.size
    }

    fun addBuild(build: Build) {
        buildList.add(build)
        notifyDataSetChanged()
    }


}