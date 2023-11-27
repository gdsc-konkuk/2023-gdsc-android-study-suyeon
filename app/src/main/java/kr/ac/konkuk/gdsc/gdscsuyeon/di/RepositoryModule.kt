package kr.ac.konkuk.gdsc.gdscsuyeon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoRepository
import kr.ac.konkuk.gdsc.gdscsuyeon.data.repository.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository

}