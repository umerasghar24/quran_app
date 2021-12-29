package com.example.quran_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quran_app.models.ParahNames

class SearchViewModel : ViewModel() {
    val parahList = MutableLiveData<List<ParahNames>>()

    //        get() {
//            return field
//        }
    val searchQuery = MutableLiveData<String?>()
//         get() {
//             return field
//         }


//
//
//    private val tasksFlow = searchQuery.flatMapLatest {
//
//    }
}