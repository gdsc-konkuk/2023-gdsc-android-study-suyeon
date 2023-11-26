package kr.ac.konkuk.gdsc.gdscsuyeon.domain

data class TodoItem(
    var id: Int,
    var todoContext: String,
    var isDone: Boolean = false
){
    companion object {
        var dummytodo = listOf<TodoItem>(

            TodoItem(1, "dummy1", false),
            TodoItem(2, "dummy2", false),
            TodoItem(3, "dummy3", true),
            TodoItem(4, "dummy4", true),
            TodoItem(5, "dummy5", false),
            TodoItem(6, "dummy6", false),
            TodoItem(7, "dummy7", true),
            TodoItem(8, "dummy8", false),
            TodoItem(9, "dummy9", false),
            TodoItem(10, "dummy10", false),
            TodoItem(11, "dummy11", false),
            TodoItem(12, "dummy12", true),
            TodoItem(13, "dummy13", false),
            TodoItem(14, "dummy14", false),
            TodoItem(15, "dummy15", false),
            TodoItem(16, "dummy16", true),
            TodoItem(17, "dummy17", false),
            TodoItem(18, "dummy18", false),
            TodoItem(19, "dummy19", false),
        )
    }
}