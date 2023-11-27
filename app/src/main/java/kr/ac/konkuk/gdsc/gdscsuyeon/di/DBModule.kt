package kr.ac.konkuk.gdsc.gdscsuyeon.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.ac.konkuk.gdsc.gdscsuyeon.data.db.TodoDao
import kr.ac.konkuk.gdsc.gdscsuyeon.data.db.TodoDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase = Room
        .databaseBuilder(context, TodoDatabase::class.java, "database")
        .build()

    @Singleton
    @Provides
    fun provideTodoDao(appDatabase: TodoDatabase): TodoDao = appDatabase.todoDao()
}