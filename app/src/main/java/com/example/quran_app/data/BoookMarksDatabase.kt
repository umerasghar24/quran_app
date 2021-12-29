package com.example.quran_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quran_app.models.BookmarksParah

@Database(entities = [BookmarksParah::class], version = 1, exportSchema = false)
abstract class BoookMarksDatabase:RoomDatabase() {
    abstract fun parahDao(): ParahDao

    companion object {
        @Volatile
        private var INSTANCE: BoookMarksDatabase? = null
        fun getDatabase(context: Context): BoookMarksDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BoookMarksDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}