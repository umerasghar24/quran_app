package com.example.quran_app.models

import android.icu.text.CaseMap
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parah_table")
data class BookmarksParah(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val page: Int,
    val title: String
)