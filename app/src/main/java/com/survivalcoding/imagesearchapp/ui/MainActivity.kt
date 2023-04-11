package com.survivalcoding.imagesearchapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.survivalcoding.imagesearchapp.domain.PhotoInfo
import com.survivalcoding.imagesearchapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val adapter: PhotoAdapter by lazy {
        PhotoAdapter()
    }

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

        // Reactive 하게 UI 수정
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        MainEvent.EndLoading -> {
                            Snackbar.make(binding.root, "로딩 완료", Snackbar.LENGTH_SHORT).show()
                        }
                        is MainEvent.ShowMessage -> {
                            Toast.makeText(applicationContext, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }


        viewModel.state.asLiveData().observe(this) { state ->
            updateUi(state.photos)
            binding.progressBar.isVisible = state.isProgress
        }

        binding.searchButton.setOnClickListener {
            // 사진 가져오기
            viewModel.fetchPhotos(binding.queryEditText.text.toString())
        }
    }

    private fun updateUi(photos: List<PhotoInfo>) {
        adapter.submitList(photos)
    }
}