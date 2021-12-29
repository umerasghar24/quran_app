package com.example.quran_app.models

import androidx.annotation.Nullable

class SurahNames(val surahNumber:Int, val title:String, val verse:Int, @Nullable val page:Int=0, @Nullable val image:String="") {
}
