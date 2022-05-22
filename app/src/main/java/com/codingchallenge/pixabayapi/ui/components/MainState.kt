package com.codingchallenge.pixabayapi.ui.components

import com.codingchallenge.pixabayapi.network.model.Hit

data class MainState(
    val isLoading:Boolean=false,
    val data:List<Hit> = emptyList(),
    val error:String=""
)
