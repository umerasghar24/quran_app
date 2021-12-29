package com.example.quran_app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quran_app.data.BoookMarksDatabase
import com.example.quran_app.models.BookmarksParah
import com.example.quran_app.repository.ParahRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ParahViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<BookmarksParah>>
    val parahRepository: ParahRepository
    init {

        val parahdao = BoookMarksDatabase.getDatabase(
            application
        ).parahDao()
        parahRepository = ParahRepository(parahdao)
        readAllData = parahRepository.readAllParahData
    }
    fun addParah(bookmarksParah: BookmarksParah){
        viewModelScope.launch(Dispatchers.IO) {
            parahRepository.addParah(bookmarksParah)
        }
    }
    fun deleteSurah(bookmarksParah: Int){
        viewModelScope.launch(Dispatchers.IO) {
            parahRepository.deleteParah(bookmarksParah)
        }
    }

//
//    fun deleteAllSurah(){
//        viewModelScope.launch(Dispatchers.IO) {
//            parahRepository.deleteAllParah()
//        }
//    }
}
