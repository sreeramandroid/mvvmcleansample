package com.rs.tmobiledemosample.di

import android.content.Context
import androidx.room.Room
import com.rs.tmobiledemosample.domain.StudentRepositoryImpl
import com.rs.tmobiledemosample.core.util.Constants.BASE_URL
import com.rs.tmobiledemosample.data.model.room.StudentDao
import com.rs.tmobiledemosample.data.model.room.StudentDatabase
import com.rs.tmobiledemosample.data.remote.StudentApiService
import com.rs.tmobiledemosample.domain.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStudentApiService(retrofit: Retrofit): StudentApiService {
        return retrofit.create(StudentApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): StudentDatabase {
        return Room.databaseBuilder(
            appContext,
            StudentDatabase::class.java,
            "student_database"
        ).build()
    }

    @Provides
    fun provideStudentDao(database: StudentDatabase): StudentDao {
        return database.studentDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindStudentRepository(
        studentRepositoryImpl: StudentRepositoryImpl
    ): StudentRepository
}