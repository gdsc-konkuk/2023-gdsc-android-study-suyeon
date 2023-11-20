package kr.ac.konkuk.gdsc.gdscsuyeon.domain

data class TodoItem(
    var id: Int = 0,
    var todoContext: String,
    var isDone: Boolean = false
){
    companion object {
        var dummytodo = listOf<TodoItem>(

            TodoItem(0, "dummy", true),
            TodoItem(1, "dummy", false),
            TodoItem(2, "dummy", false),
            TodoItem(3, "dummy", true),
            TodoItem(4, "dummy", true),
            TodoItem(5, "dummy", false),
            TodoItem(6, "dummy", false),
            TodoItem(7, "dummy", true),
            TodoItem(8, "dummy", false),
            TodoItem(9, "dummy", false),
            TodoItem(10, "dummy", false),
            TodoItem(11, "dummy", false),
            TodoItem(12, "dummy", true),
            TodoItem(13, "dummy", false),
            TodoItem(14, "dummy", false),
            TodoItem(15, "dummy", false),
            TodoItem(16, "dummy", true),
            TodoItem(17, "dummy", false),
            TodoItem(18, "dummy", false),
            TodoItem(19, "dummy", false),
        )
    }
}