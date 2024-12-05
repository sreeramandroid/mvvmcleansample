package com.rs.tmobiledemosample.domain.usecase// usecase/GetStudentsUseCase.kt
import com.rs.tmobiledemosample.data.model.Student
import com.rs.tmobiledemosample.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudentsUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend fun fetchAndStoreStudents(): Flow<Result<Unit>> = repository.fetchAndStoreStudents()

    fun getLocalStudents(): Flow<List<Student>> = repository.getLocalStudents()
}
