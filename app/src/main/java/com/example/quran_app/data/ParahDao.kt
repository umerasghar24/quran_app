package com.example.quran_app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quran_app.models.BookmarksParah

@Dao
interface ParahDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addParah(vararg bookmarkParah: BookmarksParah): Array<Long>
    @Query("DELETE FROM parah_table WHERE id = :bookmarkParah")
 fun deleteParah(bookmarkParah:Int):Void
//
//    @Query("DELETE FROM parah_table")
//    suspend fun deleteAllParah()

    @Query("SELECT * FROM parah_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BookmarksParah>>
}