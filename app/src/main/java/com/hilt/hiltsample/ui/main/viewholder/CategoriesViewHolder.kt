package com.hilt.hiltsample.ui.main.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.hilt.hiltsample.databinding.ItemCategoriesBinding
import com.hilt.hiltsample.model.Category
import com.hilt.hiltsample.ui.main.adapter.CategoriesListAdapter

class CategoriesViewHolder(private val binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Category, onItemClickListener: CategoriesListAdapter.OnItemClickListener? = null) {
        binding.postTitle.text = post.name
        //binding.postAuthor.text = post.author
       /* binding.imageView.load(post.imageUrl) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }*/

        onItemClickListener.let { listener ->
            binding.root.setOnClickListener {
                listener?.onItemClicked(post)
            }
        }
    }
}