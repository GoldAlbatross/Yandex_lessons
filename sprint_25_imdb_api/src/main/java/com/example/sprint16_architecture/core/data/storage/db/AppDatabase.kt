package com.example.sprint16_architecture.core.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sprint16_architecture.core.data.storage.db.dao.MovieDao
import com.example.sprint16_architecture.core.data.storage.db.entity.MovieEntity

@Database(version = 1, entities = [MovieEntity::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}