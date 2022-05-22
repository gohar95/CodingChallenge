package com.codingchallenge.pixabayapi.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.codingchallenge.pixabayapi.Screen
import com.codingchallenge.pixabayapi.network.model.Hit

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {

    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.list.value
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Pixabay API Search",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 25.sp
            )
            OutlinedTextField(
                value = query.value, onValueChange = {
                    query.value = it
                    viewModel.getImageList(query.value)

                }, enabled = true,
                singleLine = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },

                label = { Text(text = "Search an Image") },
                modifier = Modifier.fillMaxWidth()
            )




            if (result.isLoading) {
                Log.d("TAG", "MainContent: in the loading")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }


            if (result.error.isNotBlank()) {
                Log.d("TAG", "MainContent: ${result.error}")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = viewModel.list.value.error
                    )
                }
            }


            if (result.data.isNotEmpty()) {
                LazyVerticalGrid(cells = GridCells.Fixed(1)) {
                    viewModel.list.value.data?.let {
                        items(it) {
                            MainContentItem(it)
                        }
                    }

                }

            }


        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContentItem(hit: Hit) {
    Column() {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp),


        ) {
            Image(

                painter = rememberImagePainter(data = hit.largeImageURL),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(
//                            Color.Transparent
//                        )
//                    )
//                )
//        )

        }

//        Text(
//            text = hit.tags, modifier = Modifier
//                .offset(0.dp, 175.dp), color = Color.White
//        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {
            Text(
                text = "Image Tags :" + hit.tags,
                style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {
            Text(
                text = "Image Username :" + hit.user,
                style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {
            Text(
                text = "Image Likes :" + hit.likes,
                style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {
            Text(
                text = "Image Downloads :" + hit.downloads,
                style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)

        ) {
            Text(
                text = "Image Comments :" + hit.comments,
                style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp)
            )
        }


    }


}