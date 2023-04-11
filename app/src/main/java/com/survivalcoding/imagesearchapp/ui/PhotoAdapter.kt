package com.survivalcoding.imagesearchapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.imagesearchapp.R
import com.survivalcoding.imagesearchapp.domain.PhotoInfo
import com.survivalcoding.imagesearchapp.databinding.ItemPhotoBinding
import com.survivalcoding.imagesearchapp.util.ext.setUrl

class PhotoAdapter(
    private val onClicked: (PhotoInfo) -> Unit = {},
) : ListAdapter<PhotoInfo, PhotoAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemPhotoBinding by lazy {
            ItemPhotoBinding.bind(view)
        }

        fun bind(photo: PhotoInfo) {
            binding.imageView.setUrl(photo.previewURL)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_photo, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))

        // 클릭 이벤트 정의
        viewHolder.itemView.setOnClickListener {
            onClicked(getItem(viewHolder.adapterPosition))
        }

    }

}