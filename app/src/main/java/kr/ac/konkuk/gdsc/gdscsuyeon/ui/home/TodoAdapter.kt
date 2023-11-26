package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.RowBinding
import kr.ac.konkuk.gdsc.gdscsuyeon.domain.TodoItem

class TodoAdapter(
    private val isTodoDoneClicked : (TodoItem) -> Unit)
    : ListAdapter<TodoItem, TodoAdapter.ViewHolder>(diffUtil) {
    class ViewHolder(
        private val binding: RowBinding,
        private val isTodoDoneClicked : (TodoItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoItem) {
            binding.data = todoItem
            binding.executePendingBindings()
            binding.todoCheck.setOnClickListener {
                isTodoDoneClicked(todoItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            isTodoDoneClicked,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = currentList[position]
        holder.bind(todoItem)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TodoItem>() {
            override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}