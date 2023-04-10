package com.survivalcoding.imagesearchapp.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.imagesearchapp.R
import com.survivalcoding.imagesearchapp.data.PhotoInfo

class MainActivity : AppCompatActivity() {
    // TODO: 안전한 코드로 변경
    private lateinit var adapter: PhotoAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: ViewBinding 적용
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val queryEditText = findViewById<EditText>(R.id.query_edit_text)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter = PhotoAdapter()
        recyclerView.adapter = adapter

        // Reactive 하게 UI 수정
        viewModel.state.observe(this) { state ->
            updateUi(state.photos)
            progressBar.isVisible = state.isProgress
        }

        findViewById<ImageButton>(R.id.search_button).setOnClickListener {
            // 사진 가져오기
            viewModel.fetchPhotos(queryEditText.text.toString())
        }
    }

    private fun updateUi(photos: List<PhotoInfo>) {
        adapter.submitList(photos)
    }
}