package com.example.pixabay.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.R
import com.example.pixabay.databinding.ItemRawBinding
import com.example.pixabay.ui.model.DataUi
import com.example.pixabay.utils.LoadImage

typealias SportListener = (data: DataUi.Hit) -> Unit

class SportFragmentAdapter(private val listener: SportListener, private val loadImage: LoadImage) :
    ListAdapter<DataUi.Hit, SportFragmentAdapter.MainHolder>(ItemCallback),
    View.OnClickListener {

    override fun onClick(v: View) {
        val data = v.tag as DataUi.Hit
        if (v.id == R.id.container) listener(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = ItemRawBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        view.container.setOnClickListener(this)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val data = getItem(position)

        holder.binding.apply {
            root.tag = data
            container.tag = data
            loadImage.load(holder.itemView.context, picture, data.largeImageURL)
        }
    }

    class MainHolder(val binding: ItemRawBinding) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<DataUi.Hit>() {
        override fun areItemsTheSame(oldItem: DataUi.Hit, newItem: DataUi.Hit) =
            oldItem.previewURL == newItem.previewURL

        override fun areContentsTheSame(oldItem: DataUi.Hit, newItem: DataUi.Hit) =
            oldItem == newItem
    }
}

