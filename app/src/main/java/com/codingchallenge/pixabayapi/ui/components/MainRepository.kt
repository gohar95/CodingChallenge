package com.codingchallenge.pixabayapi.ui.components

import com.codingchallenge.pixabayapi.network.ApiService
import com.codingchallenge.pixabayapi.network.model.PixabayResponse
import com.codingchallenge.pixabayapi.util.Constant
import com.codingchallenge.pixabayapi.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService:ApiService) {


    suspend fun getQueryItems(q:String):Resource<PixabayResponse>{
      return  try{

            val result = apiService.getQueryImages(query = q, apiKey = Constant.KEY, imageType = "photo")
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }


}