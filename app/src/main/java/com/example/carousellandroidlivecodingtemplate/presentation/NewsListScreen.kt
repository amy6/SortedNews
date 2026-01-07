package com.example.carousellandroidlivecodingtemplate.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.carousellandroidlivecodingtemplate.R
import com.example.carousellandroidlivecodingtemplate.domain.model.News
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    uiState: NewsUiState,
    onEvent: (NewsUiEvent) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sorted News") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.more_options)
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Recent") },
                            onClick = {
                                onEvent(NewsUiEvent.Sort(SortType.RECENT))
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Popular") },
                            onClick = {
                                onEvent(NewsUiEvent.Sort(SortType.POPULAR))
                                showMenu = false
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE53935),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is NewsUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is NewsUiState.Success -> {
                    LazyColumn {
                        items(uiState.news) {
                            NewsListItem(news = it)
                        }
                    }
                }
                is NewsUiState.Error -> {
                    Text(text = uiState.message)
                }
                is NewsUiState.Empty -> {
                    Text(text = "No news found.")
                }
            }
        }
    }
}

@Composable
fun NewsListItem(news: News) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(news.bannerUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = news.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = formatTime(news.timeCreated),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun formatTime(timeCreated: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - (timeCreated * 1000) // Convert seconds to milliseconds

    val days = TimeUnit.MILLISECONDS.toDays(diff)
    val weeks = days / 7
    val months = days / 30
    val years = days / 365

    return when {
        years > 0 -> if (years == 1L) "1 year ago" else "$years years ago"
        months > 0 -> if (months == 1L) "1 month ago" else "$months months ago"
        weeks > 0 -> if (weeks == 1L) "1 week ago" else "$weeks weeks ago"
        else -> if (days == 1L) "1 day ago" else "$days days ago"
    }
}
