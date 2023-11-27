package kr.ac.konkuk.gdsc.gdscsuyeon.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "Todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "todoContext") var todoContext: String,
    @ColumnInfo(name = "isDone")var isDone: Boolean = false
)
