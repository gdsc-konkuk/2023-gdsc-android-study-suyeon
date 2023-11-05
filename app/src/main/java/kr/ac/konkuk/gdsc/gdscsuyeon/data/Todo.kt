package kr.ac.konkuk.gdsc.gdscsuyeon.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "Todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "todoContext") var todoContext: String,
    @ColumnInfo(name = "isDone")var isDone: Boolean = false
)
