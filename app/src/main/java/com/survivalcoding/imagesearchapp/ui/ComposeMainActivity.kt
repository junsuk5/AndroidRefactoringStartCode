package com.survivalcoding.imagesearchapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val names = (1..100).toList()

            LazyColumn {
                items(items = names) { name ->
                    ExpandableItem(text = "$name")
                }
            }
        }
    }

}

@Composable
fun ExpandableItem(text: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp
    )

    Row(
        modifier = Modifier
            .padding(24.dp)
            .background(color = Color.Gray)
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .padding(bottom = extraPadding)
        ) {
            Text(text = "Hello")
            Text(text = text)
        }
        Button(
            onClick = {
                expanded = !expanded
            }
        ) {
            Text(text = if (expanded) "줄이기" else "더 보기")
        }
    }
}

@Preview
@Composable
fun MyDesign() {
    Text(
        modifier = Modifier
            .background(color = Color.Red)
            .padding(16.dp),
        text = "Hello World"
    )
}

@Preview
@Composable
fun MyDesign2() {
    Text(
        modifier = Modifier
            .background(color = Color.Blue)
            .padding(30.dp),
        text = "Hello World !!!!!"
    )
}