package kr.ac.konkuk.gdsc.gdscsuyeon.data.db


import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase? = null

        @Synchronized
        fun getInstance(context: Fragment): TodoDatabase? {
            if (instance == null) {
                synchronized(TodoDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.requireContext(),
                            TodoDatabase::class.java,
                            "tododb"
                        ).build()
                    }
                }
            }
            return instance
        }
    }

}