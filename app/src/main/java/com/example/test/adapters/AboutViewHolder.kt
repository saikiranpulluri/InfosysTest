package com.example.test.adapters

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.MainItemBinding

class AboutViewHolder(val viewDataBinding: MainItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root)  {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.main_item
    }
}