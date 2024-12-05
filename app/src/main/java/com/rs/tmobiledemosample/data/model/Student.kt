package com.rs.tmobiledemosample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "students")
data class Student(
    @PrimaryKey val id: Int,
    val name: String,
    @SerializedName("class") val className: String,
    val percentage: Int
)