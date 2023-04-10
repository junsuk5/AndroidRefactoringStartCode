package com.survivalcoding.imagesearchapp

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.imagesearchapp.data.PhotoInfo
import com.survivalcoding.imagesearchapp.ui.PhotoAdapter

// TODO: 화면 회전시 UI 데이터 초기화 방지
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

        updateUi(viewModel.photos)

        findViewById<ImageButton>(R.id.search_button).setOnClickListener {
            // TODO: 로딩 시작
            progressBar.isVisible = true

            // 사진 가져오기
            viewModel.fetchPhotos(queryEditText.text.toString())

            // TODO: 오래 걸리는 처리
            Thread.sleep(2000)

            // TODO: 데이터 변경시 UI에 자동 반영
            updateUi(viewModel.photos)

            // TODO: 로딩 끝
            progressBar.isVisible = false
        }
    }

    private fun updateUi(photos: List<PhotoInfo>) {
        adapter.submitList(photos)
    }
}