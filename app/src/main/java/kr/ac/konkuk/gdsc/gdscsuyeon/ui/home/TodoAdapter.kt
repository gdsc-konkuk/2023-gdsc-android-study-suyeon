package kr.ac.konkuk.gdsc.gdscsuyeon.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.konkuk.gdsc.gdscsuyeon.R
import kr.ac.konkuk.gdsc.gdscsuyeon.data.Todo
import kr.ac.konkuk.gdsc.gdscsuyeon.databinding.RowBinding

class TodoAdapter(val todos: ArrayList<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    interface OnItemClickListener {
        fun OnItemClick(data: Todo, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val rowbinding: RowBinding) : RecyclerView.ViewHolder(rowbinding.root) {
        init {
            rowbinding.todoCheck.setOnClickListener {
                itemClickListener?.OnItemClick(todos[adapterPosition], adapterPosition)
            }
            rowbinding.todoText.setOnClickListener {
                itemClickListener?.OnItemClick(todos[adapterPosition], adapterPosition)
                //todo 수정
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val currentTodo = todos[position]

        holder.rowbinding.todoText.text = currentTodo.todoContext

        if (currentTodo.isDone) {
            holder.rowbinding.todoCheck.setImageResource(R.drawable.icon)
        } else {
            holder.rowbinding.todoCheck.setImageResource(R.drawable.check)
        }
    }
}