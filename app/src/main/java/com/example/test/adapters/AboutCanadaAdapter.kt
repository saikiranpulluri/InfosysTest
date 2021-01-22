package com.example.test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.database.AboutDatabaseModel
import com.example.test.databinding.MainItemBinding

class AboutCanadaAdapter : RecyclerView.Adapter<AboutViewHolder>() {
    var aboutList: List<AboutDatabaseModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val withDataBinding: MainItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AboutViewHolder.LAYOUT,
            parent,
            false
        )
        return AboutViewHolder(withDataBinding)
    }

    override fun getItemCount() = aboutList.size

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.row = aboutList[position]
            if (aboutList[position].description.isEmpty()) {
                it.description.visibility = View.GONE
            } else {
                it.description.visibility = View.VISIBLE
            }

            if (aboutList[position].imageHref.isEmpty()) {
                it.thumbnail.visibility = View.GONE
            } else {
                it.thumbnail.visibility = View.VISIBLE
            }
        }
    }
}