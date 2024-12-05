package com.rs.tmobiledemosample.domain// repository/com.rs.tmobiledemosample.domain.StudentRepositoryImpl.kt
import com.rs.tmobiledemosample.data.model.Student
import com.rs.tmobiledemosample.data.model.room.StudentDao
import com.rs.tmobiledemosample.data.remote.StudentApiService
import com.rs.tmobiledemosample.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val apiService: StudentApiService,
    private val studentDao: StudentDao
) : StudentRepository {

    override suspend fun fetchAndStoreStudents(): Flow<Result<Unit>> = flow {
        val response = apiService.getStudents()
        studentDao.insertStudents(response.students)
        emit(Result.success(Unit))
    }.catch { e ->
        emit(Result.failure(e))
    }

    override fun getLocalStudents(): Flow<List<Student>> = studentDao.getAllStudents()
}
