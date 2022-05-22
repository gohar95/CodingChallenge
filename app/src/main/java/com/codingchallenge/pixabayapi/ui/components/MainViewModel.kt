package com.codingchallenge.pixabayapi.ui.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingchallenge.pixabayapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    val list: MutableState<MainState> = mutableStateOf(MainState())


    fun getImageList(q:String)=viewModelScope.launch{
        list.value = MainState(isLoading = true)
        try{
            val result = mainRepository.getQueryItems(q)
            when(result){
                is Resource.Error->{
                    list.value = MainState(error = "Something went wrong")
                }
                is Resource.Success->{
                    result.data?.hits?.let {
                        list.value = MainState(data = it)
                    }

                }
            }
        }catch (e:Exception){
            list.value = MainState(error = "Something went wrong")
        }





    }


}