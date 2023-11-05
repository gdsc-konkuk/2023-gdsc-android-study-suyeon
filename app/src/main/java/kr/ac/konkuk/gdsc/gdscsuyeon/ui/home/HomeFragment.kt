package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.ac.konkuk.gdsc.gdscsuyeon.data.Todo
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var todoadapter: TodoAdapter
    private val todo = arrayListOf<Todo>(

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
        Todo(19, "dummy", true),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        todoadapter = TodoAdapter(todo)
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
                todoadapter.removeItem(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        todoadapter.itemClickListener = object : TodoAdapter.OnItemClickListener {
            override fun OnItemClick(data: Todo, position: Int) {
                data.isDone = !data.isDone
                todoadapter.notifyItemChanged(position)
            }

            override fun OnTodoClick(todo: Todo, position: Int) {
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
                todoadapter.notifyItemChanged(position)
            } else {
                Toast.makeText(requireContext(), "Todo item cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}