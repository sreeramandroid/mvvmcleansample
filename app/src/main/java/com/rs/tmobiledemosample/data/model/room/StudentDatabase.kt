package com.rs.tmobiledemosample.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rs.tmobiledemosample.data.model.Student

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}