package com.survivalcoding.imagesearchapp

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
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

        findViewById<ImageButton>(R.id.search_button).setOnClickListener {
            // TODO: 로딩 시작
            progressBar.isVisible = true

            // 사진 가져오기
            val photos = getPhotos(queryEditText.text.toString())

            // TODO: 오래 걸리는 처리
            Thread.sleep(2000)

            // TODO: 데이터 변경시 UI에 자동 반영
            updateUi(photos)

            // TODO: 로딩 끝
            progressBar.isVisible = false
        }
    }

    // TODO: 실제로는 오래 걸리는 처리
    private fun getPhotos(query: String): List<PhotoInfo> {
        // TODO: UI 와 데이터 분리
        return listOf(
            PhotoInfo(
                id = 0,
                previewURL = "https://cdnimg.melon.co.kr/cm2/artistcrop/images/002/61/143/261143_20210325180240_org.jpg?61e575e8653e5920470a38d1482d7312/melon/optimize/90",
                tags = "아이유, 여신"
            ),
            PhotoInfo(
                id = 1,
                previewURL = "https://image.bugsm.co.kr/album/images/500/40271/4027185.jpg",
                tags = "아이유, 가수"
            ),
            PhotoInfo(
                id = 2,
                previewURL = "https://img.gqkorea.co.kr/gq/2022/08/style_63073140eea70.jpg",
                tags = "아이유, 연기자"
            ),
        )
    }

    private fun updateUi(photos: List<PhotoInfo>) {
        adapter.submitList(photos)
    }
}