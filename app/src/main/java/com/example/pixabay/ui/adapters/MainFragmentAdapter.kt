package com.example.pixabay.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.databinding.CategoryItemRawBinding
import com.example.pixabay.ui.model.Category
import com.example.pixabay.utils.TextColorProvider

class MainFragmentAdapter(
    private val onClick: OnClick,
    private val textColorProvider: TextColorProvider,
) : RecyclerView.Adapter<MainFragmentAdapter.MainHolder>() {

    private var mList = emptyList<Category>()
    private var positionIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = CategoryItemRawBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        holder.binding.apply {
            categoryImg.setImageResource(mList[position].categoryImage)
            categoryTxt.text = mList[position].categoryName
        }

        holder.binding.categoryContainer.setOnClickListener {
            positionIndex = holder.adapterPosition
            notifyDataSetChanged()
            onClick.click(positionIndex)
        }

        if (positionIndex == position) {
            holder.binding.apply {
                categoryImg.setImageResource(mList[position].categoryImage)
                categoryTxt.setTextColor(textColorProvider.selected())
            }
        } else {
            holder.binding.apply {
                categoryImg.setImageResource(mList[position].categoryImage)
                categoryTxt.setTextColor(textColorProvider.default())
            }
        }
    }

    override fun getItemCount() = mList.size

    class MainHolder(val binding: CategoryItemRawBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<Category>) {
        val diffUtil = MainFragmentDiffUtil(mList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        mList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnClick {
        fun click(position: Int)
    }
}