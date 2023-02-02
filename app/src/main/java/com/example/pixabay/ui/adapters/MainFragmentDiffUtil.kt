package com.example.pixabay.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.pixabay.ui.model.Category

class MainFragmentDiffUtil(
    private val oldList: List<Category>,
    private val newList: List<Category>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].categoryName != newList[newItemPosition].categoryName -> false
            oldList[oldItemPosition].categoryImage != newList[newItemPosition].categoryImage -> false
            else -> true
        }
    }
}