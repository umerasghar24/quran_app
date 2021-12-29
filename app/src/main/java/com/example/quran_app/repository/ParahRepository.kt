package com.example.quran_app.repository

import androidx.lifecycle.LiveData
import com.example.quran_app.data.ParahDao
import com.example.quran_app.models.BookmarksParah

class ParahRepository(private val parahDao: ParahDao) {
    val readAllParahData: LiveData<List<BookmarksParah>> = parahDao.readAllData()
    suspend fun addParah(bookmarksParah: BookmarksParah){
        parahDao.addParah(bookmarksParah)
    }
//
suspend fun deleteParah(bookmarksParah: Int){
        parahDao.deleteParah(bookmarksParah)
    }
//
//    suspend fun deleteAllParah(){
//        parahDao.deleteAllParah()
//    }
}