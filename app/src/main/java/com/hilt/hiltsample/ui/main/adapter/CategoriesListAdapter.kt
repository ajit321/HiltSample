package com.hilt.hiltsample.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hilt.hiltsample.model.Category
import com.hilt.hiltsample.ui.main.viewholder.CategoriesViewHolder
import com.hilt.hiltsample.databinding.ItemCategoriesBinding

class CategoriesListAdapter(private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<CategoriesViewHolder>()  {
    private var mData = listOf<Category>()
    interface OnItemClickListener {
        fun onItemClicked(post: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= CategoriesViewHolder (
        ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int =mData.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) =
        holder.bind(mData[position], onItemClickListener)

    fun setData(categories: List<Category>) {
        mData=categories
    }
}