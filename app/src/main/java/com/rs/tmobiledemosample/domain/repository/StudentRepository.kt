package com.rs.tmobiledemosample.domain.repository

import com.rs.tmobiledemosample.data.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun fetchAndStoreStudents(): Flow<Result<Unit>>
    fun getLocalStudents(): Flow<List<Student>>
}