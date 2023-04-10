package com.survivalcoding.imagesearchapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.imagesearchapp.R
import com.survivalcoding.imagesearchapp.data.PhotoInfo
import com.survivalcoding.imagesearchapp.util.ext.setUrl

class PhotoAdapter(
    private val onClicked: (PhotoInfo) -> Unit = {},
) : ListAdapter<PhotoInfo, PhotoAdapter.ViewHolder>(DiffCallback) {

    // TODO: 코드 더 줄이기
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView

        init {
            imageView = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_photo, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val photo = getItem(position)

        viewHolder.imageView.setUrl(photo.previewURL)

        // 클릭 이벤트 정의
        viewHolder.itemView.setOnClickListener {
            onClicked(getItem(viewHolder.adapterPosition))
        }

    }

}