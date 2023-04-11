package com.survivalcoding.imagesearchapp.ui

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.imagesearchapp.domain.PhotoInfo

object DiffCallback : DiffUtil.ItemCallback<PhotoInfo>() {
    override fun areItemsTheSame(oldItem: PhotoInfo, newItem: PhotoInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoInfo, newItem: PhotoInfo): Boolean {
        return oldItem == newItem
    }
}