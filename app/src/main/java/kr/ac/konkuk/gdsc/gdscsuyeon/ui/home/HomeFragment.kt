package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.ac.konkuk.gdsc.gdscsuyeon.data.Todo
import kr.ac.konkuk.gdsc.gdscsuyeon.data.TodoDatabase
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?= null
    private val binding
        get() = requireNotNull(_binding) {"HomeFragment binding is null"}
    private lateinit var db: TodoDatabase
    private lateinit var todoadapter: TodoAdapter
    private lateinit var todo: ArrayList<Todo>
    private lateinit var recordset: List<Todo>
    private val dummytodo = listOf<Todo>(

        Todo(0, "dummy", true),
        Todo(1, "dummy", false),
        Todo(2, "dummy", false),
        Todo(3, "dummy", true),
        Todo(4, "dummy", true),
        Todo(5, "dummy", false),
        Todo(6, "dummy", false),
        Todo(7, "dummy", true),
        Todo(8, "dummy", false),
        Todo(9, "dummy", false),
        Todo(10, "dummy", false),
        Todo(11, "dummy", false),
        Todo(12, "dummy", true),
        Todo(13, "dummy", false),
        Todo(14, "dummy", false),
        Todo(15, "dummy", false),
        Todo(16, "dummy", true),
        Todo(17, "dummy", false),
        Todo(18, "dummy", false),
        Todo(19, "dummy", false),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initdb()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        return binding.root
    }

    private fun initdb() {
        db = TodoDatabase.getInstance(this@HomeFragment)!!

        CoroutineScope(Dispatchers.IO).launch {
            if (db.todoDao().getAllTodo().isEmpty()) {
                dummytodo.map { item ->
                    db.todoDao().insertTodo(item)
                }
            }
            val loadedData = db.todoDao().getAllTodo() as ArrayList<Todo>
            recordset = loadedData
            withContext(Dispatchers.Main) {
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        todoadapter = TodoAdapter(recordset as ArrayList<Todo>)
        binding.recyclerView.adapter = todoadapter
        //recyclerview 구분해주는 구분자 추가
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                todoadapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val data = recordset[viewHolder.adapterPosition]
                CoroutineScope(Dispatchers.IO).launch {
                    db.todoDao().deleteTodo(data)
                    getAllRecord()
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        todoadapter.itemClickListener = object : TodoAdapter.OnItemClickListener {
            override fun onItemClick(data: Todo, position: Int) {
                data.isDone = !data.isDone
                CoroutineScope(Dispatchers.IO).launch {
                    db.todoDao().updateTodo(data)
                    getAllRecord()
                }
            }

            override fun onTodoClick(todo: Todo, position: Int) {
                showEditTodoDialog(todo, position)
            }
        }
    }

    private fun showEditTodoDialog(todo: Todo, position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Todo 수정")

        val input = EditText(requireContext())
        input.setText(todo.todoContext)
        builder.setView(input)

        builder.setPositiveButton("Save") { _, _ ->
            val newTodoContext = input.text.toString()
            if (newTodoContext.isNotEmpty()) {
                todo.todoContext = newTodoContext
                CoroutineScope(Dispatchers.IO).launch {
                    db.todoDao().updateTodo(todo)
                    getAllRecord()
                }
            } else {
                showSnackbar("잘못된 입력값입니다.")
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    suspend fun getAllRecord() {
        recordset = db.todoDao().getAllTodo() as ArrayList<Todo>
        todoadapter.todos = recordset as ArrayList<Todo>
        CoroutineScope(Dispatchers.Main).launch {
            todoadapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}