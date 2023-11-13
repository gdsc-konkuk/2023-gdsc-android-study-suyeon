package kr.ac.konkuk.gdsc.gdscsuyeon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.ac.konkuk.gdsc.gdscsuyeon.ui.home.HomeFragment

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase?= null

        @Synchronized
        fun getInstance(context: HomeFragment): TodoDatabase? {
            if (instance == null) {
                synchronized(TodoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.requireContext(),
                        TodoDatabase::class.java,
                        "tododb"
                    ).build()
                }
            }
            return instance
        }
    }
}