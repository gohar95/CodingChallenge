package com.codingchallenge.pixabayapi

sealed class Screen(val route : String)
{
    object MainContent : Screen("main_content")
    object DetailScreen : Screen("detail_screen")


}
